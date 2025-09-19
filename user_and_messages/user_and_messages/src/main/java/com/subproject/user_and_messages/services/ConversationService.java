package com.subproject.user_and_messages.services;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.subproject.user_and_messages.dtos.conversation.ConversationPreviewDTO;

public interface ConversationService {

    public Page<ConversationPreviewDTO> getInboxForUser(String userNickname, Long conversationId, Pageable pageable);

	public Page<ConversationPreviewDTO> getSentForUser(String userNickname, Long conversationId, Pageable pageable);
	public Page<ConversationPreviewDTO> getArchivedForUser(String userNickname, Long conversationId, Pageable pageable);
	public Page<ConversationPreviewDTO> getSpamForUser(String userNickname, Long conversationId, Pageable pageable);
	public Page<ConversationPreviewDTO> getTrashForUser(String userNickname, Long conversationId, Pageable pageable);

    
}
