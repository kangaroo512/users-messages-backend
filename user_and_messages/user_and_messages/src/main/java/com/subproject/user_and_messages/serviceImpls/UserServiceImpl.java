package com.subproject.user_and_messages.serviceImpls;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.subproject.user_and_messages.dtos.UserLanguageDTO;
import com.subproject.user_and_messages.dtos.language.LanguageFormPreviewDTO;
import com.subproject.user_and_messages.dtos.response.UserSearchResponse;
import com.subproject.user_and_messages.dtos.user.UserCreateDTO;
import com.subproject.user_and_messages.dtos.user.UserFormPreviewDTO;
import com.subproject.user_and_messages.dtos.user.UserPreviewDTO;
import com.subproject.user_and_messages.dtos.user.UserUpdateDTO;
import com.subproject.user_and_messages.dtos.userLanguage.UserLanguageFormPreviewDTO;
import com.subproject.user_and_messages.entities.Hobby;
import com.subproject.user_and_messages.entities.Language;
import com.subproject.user_and_messages.entities.Location;
import com.subproject.user_and_messages.entities.TypeOfExchange;
import com.subproject.user_and_messages.entities.User;
import com.subproject.user_and_messages.entities.UserLanguage;
import com.subproject.user_and_messages.entities.embeddable.UserLanguageId;
import com.subproject.user_and_messages.entities.enums.ChatSoftware;
import com.subproject.user_and_messages.entities.enums.Country;
import com.subproject.user_and_messages.entities.enums.Gender;
import com.subproject.user_and_messages.entities.enums.LanguageProficiency;
import com.subproject.user_and_messages.mappers.UserMapper;
import com.subproject.user_and_messages.mappers.user.UserSearchMapper;
import com.subproject.user_and_messages.repositories.HobbyRepository;
import com.subproject.user_and_messages.repositories.LanguageRepository;
import com.subproject.user_and_messages.repositories.LocationRepository;
import com.subproject.user_and_messages.repositories.UserRepository;
import com.subproject.user_and_messages.search.filter.UserSearchCriteria;
import com.subproject.user_and_messages.search.spec.UserSpecification;
import com.subproject.user_and_messages.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    private LanguageRepository languageRepository;

    private LocationRepository locationRepository;

    private HobbyRepository hobbyRepository;

    private UserSearchMapper userSearchMapper;


    public UserServiceImpl(UserRepository userRepository, 
    UserMapper userMapper, 
    LanguageRepository languageRepository,
    LocationRepository locationRepository,
    HobbyRepository hobbyRepository,
    UserSearchMapper userSearchMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.languageRepository = languageRepository;
        this.locationRepository = locationRepository;
        this.hobbyRepository = hobbyRepository;
        this.userMapper = userMapper;
        this.userSearchMapper = userSearchMapper;
        
    }

    @Override
    public Page<UserSearchResponse> searchUsers(UserSearchCriteria userCriteria, Pageable pageable) {

        Specification<User> spec = (root, query, cb) -> cb.conjunction();

        if(userCriteria.getNickname() != null) {
            spec = spec.and(UserSpecification.hasNickname(userCriteria.getNickname()));
        }

        if(userCriteria.getGender() != null) {
            spec = spec.and(UserSpecification.hasGender(userCriteria.getGender()));
        }

        if(userCriteria.getFromAge() != null || userCriteria.getToAge() != null) {
            spec = spec.and(UserSpecification.hasAge(userCriteria.getFromAge(), userCriteria.getToAge()));
        }

        if(userCriteria.getHobbyNames() != null) {
            spec = spec.and(UserSpecification.hasHobbies(userCriteria.getHobbyNames()));
        }

        if(userCriteria.getLocation() != null) {
            spec = spec.and(UserSpecification.hasAnyLocationMatch(userCriteria.getLocation()));
        }

        if(userCriteria.getSpokenLanguage() != null) {
            spec = spec.and(UserSpecification.hasAnySpokenLanguageMatch(userCriteria.getSpokenLanguage()));
        }

        if(userCriteria.getPracticeLanguage() != null) {
            spec = spec.and(UserSpecification.hasAnyPracticeLanguageMatch(userCriteria.getPracticeLanguage()));
        }

        if(userCriteria.getContactMethod() != null) {
            spec = spec.and(UserSpecification.hasContactMethod(userCriteria.getContactMethod()));
        }

        return userRepository.findAll(spec, pageable).map(userSearchMapper::userToResponse);

    }

    @Override
    public UserPreviewDTO findById(Long id) {
        return userRepository.findById(id)
        .map(userMapper::userToPreview)
        .orElseThrow();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserPreviewDTO updateUser(UserUpdateDTO userUpdateDto, Long id) {
        User user = userRepository.findById(id).orElseThrow();

        userMapper.userUpdateDTOToUser(userUpdateDto, user);

        


        //Update Location - MODIFY LATER - MAKE THE LOCATION IMMUTABLE SINCE IS SHARED BY MANY USERS
        Location location = locationRepository
        .findByCountryAndTownIgnoreCaseAndStateProvinceIgnoreCase(
            Country.valueOf(userUpdateDto.getLocation().getCountry().toUpperCase()), userUpdateDto.getLocation().getTown(),
             userUpdateDto.getLocation().getStateProvince()).orElseGet(
                () -> {
                Location newLocation = new Location();
                newLocation.setCountry(Country.valueOf(userUpdateDto.getLocation().getCountry().toUpperCase()));
                newLocation.setTown(userUpdateDto.getLocation().getTown());
                newLocation.setStateProvince(userUpdateDto.getLocation().getStateProvince());

                return locationRepository.save(newLocation);
             });

        user.setLocation(location);
            

        /* 
        //Update UserLanguage
        List<UserLanguage> userLanguages = userUpdateDto.getUserLanguageDTOs().stream()
        .map(userLangDto -> {

            LanguageDTO languageDto = userLangDto.getLanguage();

            Language language = languageRepository.findByIsoCode(languageDto.getIsoCode())
            .orElseGet(() -> {
                Language newLang = new Language();
                newLang.setName(languageDto.getName());
                newLang.setIsoCode(languageDto.getIsoCode());
                return languageRepository.save(newLang);
            });

            UserLanguageId userLangId = new UserLanguageId();
            userLangId.setUserId(user.getId());
            userLangId.setLanguageId(language.getId());

            UserLanguage userLanguage = new UserLanguage();
            userLanguage.setUser(user);
            userLanguage.setLanguage(language);
            userLanguage.setUserLanguageId(userLangId);
            userLanguage.setProficiencyLevel(LanguageProficiency.valueOf(userLangDto.getProficiencyLevel().toUpperCase()));

            
            return userLanguage;

        })
        .collect(Collectors.toList());

        user.getUserLanguages().clear();
        user.setUserLanguages(userLanguages);
        */

        //update user languages
        updateUserLanguages(user, userUpdateDto.getUserLanguageDTOs());


        //Update TypeOfExchange

        user.getTypeOfExchange().setChatSoftware(ChatSoftware.valueOf(
          userUpdateDto.getTypeOfExchange().getChatSoftware().toUpperCase()  
        ));
        user.getTypeOfExchange().setCorrespondancePenPal(userUpdateDto.getTypeOfExchange().getCorrespondencePenPal());
        user.getTypeOfExchange().setFaceToFaceConversation(userUpdateDto.getTypeOfExchange().getFaceToFaceConversation());


        


        //Update hobbies
        List<Hobby> hobbies = userUpdateDto.getHobbies().stream()
        .map(p -> {
            Hobby hobby = hobbyRepository.findByName(p).orElseGet(() -> {
                Hobby newHobby = new Hobby();
                newHobby.setName(p);
                return hobbyRepository.save(newHobby);
            });
            return hobby;
        })
        .collect(Collectors.toList());

        user.getHobbies().clear();
        user.setHobbies(hobbies);



        User savedUser = userRepository.save(user);

        return userMapper.userToPreview(savedUser);


        
    }

    @Transactional
    @Override
    public UserPreviewDTO createUser(UserCreateDTO userCreateDTO) {
        
        User user = new User();

        //String nickname
        user.setNickname(userCreateDTO.getNickname());

        //String password
        user.setPassword(userCreateDTO.getPassword());

        //String email
        user.setEmail(userCreateDTO.getEmail());

        //Location location

        Location location = 
        locationRepository.findByCountryAndTownIgnoreCaseAndStateProvinceIgnoreCase(
            Country.valueOf(userCreateDTO.getLocation().getCountry().toUpperCase()),
            userCreateDTO.getLocation().getTown(),
            userCreateDTO.getLocation().getStateProvince()).orElseGet(() -> {
                Location newLocation = new Location();
                newLocation.setCountry(Country.valueOf(userCreateDTO.getLocation().getCountry().toUpperCase()));
                newLocation.setStateProvince(userCreateDTO.getLocation().getStateProvince());
                newLocation.setTown(userCreateDTO.getLocation().getTown());
                return locationRepository.save(newLocation);
            });

        user.setLocation(location);

        //Gender gender

        user.setGender(Gender.valueOf(userCreateDTO.getGender().toUpperCase()));

        //LocalDate birthdate

        user.setBirthdate(userCreateDTO.getBirthDate());

        //List<UserLanguage> userLanguage

        List<UserLanguage> userLanguages = userCreateDTO.getUserLanguageDTOs().stream()
        .map(p -> {

            Language language = languageRepository.findByIsoCode(p.getLanguage().getIsoCode()).orElseThrow();

            UserLanguageId userLanguageId = new UserLanguageId();
            userLanguageId.setUserId(user.getId());
            userLanguageId.setLanguageId(language.getId());

            UserLanguage userLanguage = new UserLanguage();
            userLanguage.setProficiencyLevel(LanguageProficiency.valueOf(p.getProficiencyLevel().toUpperCase()));
            userLanguage.setUserLanguageId(userLanguageId);
            userLanguage.setLanguage(language);
            userLanguage.setUser(user);

            return userLanguage;
        })
        .collect(Collectors.toList());

        user.setUserLanguages(userLanguages);
        

        //TypeOfExchange typeOfExchange

        TypeOfExchange typeOfExchange = new TypeOfExchange();
        typeOfExchange.setChatSoftware(ChatSoftware.valueOf(userCreateDTO.getTypeOfExchange().getChatSoftware().toUpperCase()));
        typeOfExchange.setCorrespondancePenPal(userCreateDTO.getTypeOfExchange().getCorrespondencePenPal());
        typeOfExchange.setFaceToFaceConversation(userCreateDTO.getTypeOfExchange().getFaceToFaceConversation());

        user.setTypeOfExchange(typeOfExchange);

        //List<Hobby> hobbies

        List<Hobby> hobbies = userCreateDTO.getHobbies().stream()
        .map(p -> {
            Hobby hobby = hobbyRepository.findByName(p.getName()).orElseGet(() -> {
                Hobby newHobby = new Hobby();
                newHobby.setName(p.getName());
                return hobbyRepository.save(newHobby);
            });

            return hobby;
        })
        .collect(Collectors.toList());

        user.setHobbies(hobbies);

        //String description

        user.setDescription(userCreateDTO.getDescription());

        userRepository.save(user);

        return userMapper.userToPreview(user);
        
    }

    @Override
    public UserPreviewDTO getUserByNickname(String nickname) {
        User user = userRepository.getByNickname(nickname).orElseThrow();
        UserPreviewDTO userPreview = this.userMapper.userToPreview(user);
        System.out.println(userPreview.getDescription());
        System.out.println(userPreview.getGender());
        System.out.println(userPreview.getNativeLanguages());
        System.out.println(userPreview.getPracticingLanguages());
        System.out.println(userPreview.getTypeOfExchange().getChatSoftware());
        System.out.println(userPreview.getTypeOfExchange().getCorrespondencePenPal());
        System.out.println(userPreview.getTypeOfExchange().getFaceToFaceConversation());
        return userPreview;

    }

    private void updateUserLanguages(User user, List<UserLanguageDTO> incomingDTOs) {

        List<UserLanguage> currentUserLanguages = user.getUserLanguages();

        // part 1: build map of incomming dtos
        Map<UserLanguageId, UserLanguageDTO> incomingMap = new HashMap<>();
        for(UserLanguageDTO dto : incomingDTOs) {
            Language language = languageRepository.findByIsoCode(dto.getLanguage().getIsoCode()).orElseThrow();

            UserLanguageId id = new UserLanguageId();
            id.setLanguageId(language.getId());
            id.setUserId(user.getId());
            incomingMap.put(id, dto);
        }

        //part 2: remove UserLanguages not in incoming list
        Iterator<UserLanguage> iterator = currentUserLanguages.iterator();
        while(iterator.hasNext()) {
            UserLanguage existingUL = iterator.next();
            if(!incomingMap.containsKey(existingUL.getUserLanguageId())) {
                iterator.remove(); // triggers orphan removal
            }
        }

        //step 3: update or add new UserLanguages
        for(Map.Entry<UserLanguageId, UserLanguageDTO> entry : incomingMap.entrySet()) {
            UserLanguageId id = entry.getKey();
            UserLanguageDTO dto = entry.getValue();
            
            boolean exists = currentUserLanguages.stream()
            .anyMatch(ul -> ul.getUserLanguageId().equals(id));

            if(!exists) {
                Language language = languageRepository.findByIsoCode(dto.getLanguage().getIsoCode()).orElseThrow();

                UserLanguage newUL = new UserLanguage();
                newUL.setUserLanguageId(id);
                newUL.setUser(user);
                newUL.setLanguage(language);
                newUL.setProficiencyLevel(LanguageProficiency.valueOf(dto.getProficiencyLevel().toUpperCase()));
                currentUserLanguages.add(newUL); // cascade
            } else {
                currentUserLanguages.stream()
                .filter(ul -> ul.getUserLanguageId().equals(id))
                .findFirst()
                .ifPresent(existing -> {
                    LanguageProficiency newProf = LanguageProficiency.valueOf(dto.getProficiencyLevel().toUpperCase());
                    if(!existing.getProficiencyLevel().equals(newProf)) {
                        existing.setProficiencyLevel(newProf);
                    }
                });
            }
            
        }
    }

    @Override
    public UserFormPreviewDTO getUserFormDTOByNickname(String nickname) {
        User user = userRepository.getByNickname(nickname).orElseThrow();
        UserFormPreviewDTO userFormPreview = userMapper.userToUserFormPreviewDTO(user);
        
        //gert list of UserLanguageDTO native proficiency only
        List<UserLanguageFormPreviewDTO> nativeLanguages = user.getUserLanguages().stream()
        .filter(p -> p.getProficiencyLevel() == LanguageProficiency.NATIVE)
        .map(p -> {
            UserLanguageFormPreviewDTO userLanguageFormPreviewDTO = new UserLanguageFormPreviewDTO();
            userLanguageFormPreviewDTO.setLanguage(LanguageFormPreviewDTO.builder()
            .name(p.getLanguage().getName())
            .isoCode(p.getLanguage().getIsoCode())
            .build());

            userLanguageFormPreviewDTO.setProficiencyLevel(p.getProficiencyLevel().toString());

            return userLanguageFormPreviewDTO;
        })
        .collect(Collectors.toList());

        //get a list of UserLanguageDTO other proficiency than native only
        List<UserLanguageFormPreviewDTO> practicingLanguages = user.getUserLanguages().stream()
        .filter(p -> p.getProficiencyLevel() != LanguageProficiency.NATIVE)
        .map(p -> {
            UserLanguageFormPreviewDTO userLanguageFormPreviewDTO = new UserLanguageFormPreviewDTO();
            userLanguageFormPreviewDTO.setLanguage(LanguageFormPreviewDTO.builder()
            .name(p.getLanguage().getName())
            .isoCode(p.getLanguage().getIsoCode())
            .build());

            userLanguageFormPreviewDTO.setProficiencyLevel(p.getProficiencyLevel().toString());

            return userLanguageFormPreviewDTO;
        })
        .collect(Collectors.toList());
        
        //map languages
        userFormPreview.setNativeLanguages(nativeLanguages);
        userFormPreview.setPracticingLanguages(practicingLanguages);

        System.out.println(userFormPreview.toString());

        return userFormPreview;



        
    }

    @Override
    public Long getUserIdByNickname(String nickname) {

        return this.userRepository.getByNickname(nickname).orElseThrow().getId();
        
    }
    

    
}
