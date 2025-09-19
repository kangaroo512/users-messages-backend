package com.subproject.user_and_messages.dtos.conversation;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class ConversationPreviewDTO {
    private Long conversationId;

    private String otherUserNickname;

    private String latestMessageContent;
    private OffsetDateTime latestMessageSentAt;
    private boolean latestMessageSentByCurrentUser;
    private Long numberOfMessages;

    private boolean isRead;

    
}
