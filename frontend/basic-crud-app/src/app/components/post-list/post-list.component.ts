import { Component ,OnInit} from '@angular/core';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-post-list',
  standalone: true,
  imports: [],
  templateUrl: './post-list.component.html',
  styleUrl: './post-list.component.css'
})
export class PostListComponent implements OnInit {


  posts: any[] = [];
  
  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.postService.getPosts().subscribe({
      next: (response) => {
        console.log(response);
        this.posts = response;
      },
      error: (error) => {
        console.error('Error fetching posts:', error);
      }
    });
  }

}
