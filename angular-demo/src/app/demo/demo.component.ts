import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css']
})
export class DemoComponent implements OnInit {

  constructor() { }

  user = {
    content: 'Hello',
    newContent: "Hi",
    palCheck: false,
    number: 0,
    numbers: [1, 2, 3],
  };

  updateContent(): void {
    this.user.content += ' ' + this.user.newContent;
  }
  // one which takes user input as a string and tests whether it is a palindrome, then displays the result
  palindromeChecker(): boolean {
    let newContent = this.user.newContent;
    for (let i = 0; i < newContent.length / 2; i++) {
      if (newContent.charAt(i) != newContent.charAt(newContent.length - 1 - i))
        return this.user.palCheck = false;
    }
    return this.user.palCheck = true;
  }
  // one which takes a user-provided array of numbers (you can have the user enter the numbers one at a time or as 
  // a single comma-separated sequence), sorts the array, and displays the sorted version. 
  arrayAdder(): void {
    this.user.numbers.push(this.user.number);
  }
  ngOnInit() {
  }

}
