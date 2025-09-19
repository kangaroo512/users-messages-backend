package com.subproject.user_and_messages.search.spec;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.subproject.user_and_messages.entities.Hobby;
import com.subproject.user_and_messages.entities.Location;
import com.subproject.user_and_messages.entities.User;
import com.subproject.user_and_messages.entities.UserLanguage;
import com.subproject.user_and_messages.entities.enums.ChatSoftware;
import com.subproject.user_and_messages.entities.enums.Gender;
import com.subproject.user_and_messages.search.filter.LanguagePracticeFilter;
import com.subproject.user_and_messages.search.filter.LocationFilter;
import com.subproject.user_and_messages.search.filter.SpokenLanguageFilter;
import com.subproject.user_and_messages.search.filter.TypeOfExchangeFilter;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class UserSpecification {

    public static Specification<User> hasAnySpokenLanguageMatch(SpokenLanguageFilter spoken) {

        return (root, query, cb) -> {
            if(validateSpokenLang(spoken)) return null;

            Join<User, UserLanguage> userLang = root.join("userLanguages", JoinType.INNER);

            //check if any user has a native language with the spoken.name
            
            Predicate predicateSpoken = cb.and(
                cb.equal(userLang.get("language").get("name"), spoken.getName()),
                cb.equal(userLang.get("proficiencyLevel"), spoken.getLevel())
            );

            //check if any user has a practice language with the practice.name AND practice.range(fromLevel, toLevel)

            return predicateSpoken;
        };
    }


    public static Specification<User> hasAnyPracticeLanguageMatch(LanguagePracticeFilter practice) {
        return (root, query, cb) -> {
            if(validatePracticeLang(practice)) return null;

            Join<User, UserLanguage> userLang = root.join("userLanguages", JoinType.INNER);


            Predicate predicatePractice = cb.and(
                cb.equal(userLang.get("language").get("name"), practice.getLanguageName()),
                cb.between(userLang.get("proficiencyLevel"), practice.getFromLevel(), practice.getToLevel())
            );

            return predicatePractice;
        };

    }

    private static boolean validatePracticeLang(LanguagePracticeFilter lang) {
        return lang.getLanguageName() == null || lang.getLanguageName().isEmpty()
         || lang.getFromLevel() != null || lang.getToLevel() != null;
    }

    private static boolean validateSpokenLang(SpokenLanguageFilter lang) {
        return lang == null || lang.getLevel() == null || lang.getName() == null || lang.getName().isEmpty();
    }



    public static Specification<User> hasAnyLocationMatch(LocationFilter filter) {

        if(filter == null) return null;
        return (root, query, cb) -> {
            Join<User, Location> userLoc = root.join("location", JoinType.INNER);

            String country = filter.getCountry();
            String city = filter.getCity();
            String state = filter.getState();

            boolean isCountry = country != null && !country.isEmpty();
            boolean isCity = city != null && !city.isEmpty();
            boolean isState = state != null && !state.isEmpty();

            List<Predicate> andPredicates = new ArrayList<>();

            if(isCountry) {
                andPredicates.add(cb.equal(userLoc.get("country"), filter.getCountry()));
            }

            if(isCity) {
                andPredicates.add(cb.equal(userLoc.get("town"), filter.getCity()));
            }

            if(isState) {
                andPredicates.add(cb.equal(userLoc.get("stateProvince"), filter.getState()));
            }

            return andPredicates.isEmpty() ? null : cb.and(andPredicates.toArray(new Predicate[0]));
        };

    }

    public static Specification<User> hasNickname(String nickname) {
        return (root, query, cb) -> {
            if(nickname == null || nickname.trim().isEmpty()) return null;
            return cb.equal(root.get("nickname"), nickname);
        };
    }

    public static Specification<User> hasGender(Gender gender) {
        return (root, query, cb) -> {
            if(gender == null) return null;
            return cb.equal(root.get("gender"), gender);
        };

    }


    public static Specification<User> hasAge(Integer fromAge, Integer toAge) {


        return (root, query, cb) -> {
            if(fromAge == null || toAge == null || fromAge < 0 || toAge < 0 || fromAge > toAge) {
            return null;
            }
            LocalDate fromDate = LocalDate.now().minusYears(fromAge);
            LocalDate toDate = LocalDate.now().minusYears(toAge);

            return cb.between(root.get("birthdate"), fromDate, toDate);
        };

        
    }

    public static Specification<User> hasContactMethod(TypeOfExchangeFilter filter) {

        if(filter == null) return null;
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filter.getChatSoftware() != null && !"ANY".equalsIgnoreCase(filter.getChatSoftware())) {
                predicates.add(
                    cb.equal(
                        root.get("typeOfExchange").get("chatSoftware"),
                            ChatSoftware.valueOf(filter.getChatSoftware().toUpperCase())));
            }


            if(filter.isCorrespondencePenPal()) {
                predicates.add(
                    cb.equal(root.get("typeOfExchange").get("correspondancePenpal"),
                    filter.isCorrespondencePenPal())
                );
            }


            if(filter.isFaceToFaceConversation()) {
                predicates.add(
                    cb.equal(root.get("typeOfExchange").get("faceToFaceConversation"),
                    filter.isFaceToFaceConversation())
                );
            }

            return predicates.isEmpty() ? null : cb.and(predicates.toArray(new Predicate[0]));


        };

        
    }

    public static Specification<User> hasHobbies(List<String> hobbies) {
        
        return (root, query, cb) -> {

            if(hobbies == null || hobbies.isEmpty()) return null;

            Join<User, Hobby> userHobbies = root.join("hobbies");
            return userHobbies.get("name").in(hobbies);

        };
    }


}
