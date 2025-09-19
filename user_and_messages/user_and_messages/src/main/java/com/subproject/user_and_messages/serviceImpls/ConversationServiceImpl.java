package com.subproject.user_and_messages.serviceImpls;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.subproject.user_and_messages.dtos.conversation.ConversationPreviewDTO;
import com.subproject.user_and_messages.services.ConversationService;

public class ConversationServiceImpl implements ConversationService {

    @Override
    public Page<ConversationPreviewDTO> getInboxForUser(String userNickname, Long conversationId, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInboxForUser'");
    }

    @Override
    public Page<ConversationPreviewDTO> getSentForUser(String userNickname, Long conversationId, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSentForUser'");
    }

    @Override
    public Page<ConversationPreviewDTO> getArchivedForUser(String userNickname, Long conversationId,
            Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArchivedForUser'");
    }

    @Override
    public Page<ConversationPreviewDTO> getSpamForUser(String userNickname, Long conversationId, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpamForUser'");
    }

    @Override
    public Page<ConversationPreviewDTO> getTrashForUser(String userNickname, Long conversationId, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTrashForUser'");
    }
    
}
