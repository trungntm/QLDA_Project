import { Component, OnInit, Injectable } from '@angular/core';
import { UploadFileService } from '../service/upload-file.service';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  selectedFile: File;
  constructor(private uploadService: UploadFileService) { }

  ngOnInit() {
  }

  onFileChanged(e) {
    const file = e.target.files[0];
    console.log(file);
  }
}
