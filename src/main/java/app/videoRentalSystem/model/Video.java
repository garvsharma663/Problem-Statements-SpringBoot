package app.videoRentalSystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "videos")
public class Video {
    @Getter @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title of the Video.
    @Getter
    @Setter
    @NotNull(message = "Title cannot be left Blank")
    @Size(max = 100, message = "Max title length is 100 letters") // This constraint annotation is used for String for an Integer we use @Max() or @Min()
    @Column(nullable = false)
    private String title;

    @Getter
    @Setter
    @Column(nullable = false)
    // Check In/Out variable.
    private boolean checked = false;

    @Getter
    @Setter
    @Column(nullable = false)
    @Max(5) @Min(0)
    // User Rating
    private float rating = 0.0f;

    // Default Constructor for our DB
    public Video(){}

    // Parameterized Constructor
    public Video(String title, boolean checked, float rating){
        this.title = title;
        this.checked = checked; // Default checkout, False.
        this.rating = rating; // Default Rating, 0.0.
    }


    // Additional Helper Methods.
    public void toggleChecked(boolean currentState){
        this.checked = !currentState;
    }
    public void returnVideo(){
        this.checked = false;
    }
    public void updateRating(float rating){
        this.rating = rating;
    }


}
