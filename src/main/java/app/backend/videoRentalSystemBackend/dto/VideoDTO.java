package app.backend.videoRentalSystemBackend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoDTO {
    private Long id;

    @NotNull(message = "Title cannot be left Blank")
    @Size(max = 100, message = "Max title length is 100 letters")
    private String title;

    private boolean checked;

    @Max(5)
    @Min(0)
    private float rating;

    // Now we'll create helper methods in VideoService or in another Mapper file is we want to convert the model entities to DTO.
    // And we'll perform operations on DTO.
}
