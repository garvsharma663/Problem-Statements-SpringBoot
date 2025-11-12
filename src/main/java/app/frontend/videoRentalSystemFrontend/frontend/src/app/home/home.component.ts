import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  videos: any[] = [];
  newVideo = { title: '', rating: 0 };

  constructor(private http: HttpClient) {
    this.fetchVideos();
  }

  fetchVideos() {
    this.http.get<any[]>('http://localhost:8080/api/videos').subscribe({
      next: (data) => (this.videos = data),
      error: (err) => console.error('❌ Error fetching videos:', err)
    });
  }

  addVideo() {
    this.http.post('http://localhost:8080/api/videos', this.newVideo).subscribe({
      next: (res) => {
        this.videos.push({ ...this.newVideo });
        this.newVideo = { title: '', rating: 0 };
      },
      error: (err) => console.error('❌ Error adding video:', err)
    });
  }
}
