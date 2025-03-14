package org.ukstagram.domain.music.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.ukstagram.domain.music.service.MusicService;
import org.ukstagram.global.exception.ErrorCode;
import org.ukstagram.global.pricipal.AuthPrincipal;
import org.ukstagram.global.pricipal.UserAuth;
import org.ukstagram.global.response.Response;

@RestController
@RequestMapping("/music")
@AllArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<String> uploadMusicFile(@AuthPrincipal UserAuth user,
                                            @RequestParam("file") MultipartFile file,
                                            @RequestParam(name = "title", required = true) String title,
                                            @RequestParam(name = "genre", required = true) String genre,
                                            @RequestParam(name = "mood", required = true) String mood,
                                            @RequestParam(name = "theme", required = true) String theme,
                                            @RequestParam(name = "description", required = true) String description,
                                            @RequestParam(name = "tags", required = true) String tags
    ) throws Exception {
        if (file.isEmpty()) {
            return Response.error(ErrorCode.INVALID_INPUT_VALUE);
        }

        musicService.uploadMusicFile(user, file, title, genre, mood, theme, description, tags);

        return Response.ok("업로드 성공했습니다.");
    }

    @GetMapping
    public String getMusicList(){
        return "main/main";
    }

}
