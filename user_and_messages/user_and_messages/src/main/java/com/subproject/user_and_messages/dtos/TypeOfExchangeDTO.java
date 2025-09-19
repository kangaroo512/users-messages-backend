package com.subproject.user_and_messages.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfExchangeDTO {
    @NotNull(message = "Required")
    private Boolean correspondencePenPal;

    @NotNull(message = "Required")
    private Boolean faceToFaceConversation;

    @Pattern(regexp = 
    "WHATSAPP|TELEGRAM|SIGNAL|FACEBOOK_MESSENGER|INSTAGRAM|SNAPCHAT|WECHAT|LINE|VIBER|DISCORD|SKYPE|MICROSOFT_TEAMS|SLACK|ZOOM_CHAT|GOOGLE_CHAT|THREEMA|KIK|ICQ|TANGO|IMO",
    message="Select one chat software")
    @NotBlank(message = "Must include a chat software")
    private String chatSoftware;
}
