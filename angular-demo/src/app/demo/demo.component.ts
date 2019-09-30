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
    this.user.numbers = this.user.numbers.concat(this.user.number);
  }
  bubbleSort(): void{
    let flag: boolean = true;
    let tempArray :number[] = this.user.numbers;
    while (flag) {
      flag = false;
      for (let i = 0; i < tempArray.length; i++) {
        if (tempArray[i] > tempArray[i + 1]) {
          let temp = tempArray[i + 1];
          tempArray[i + 1] = tempArray[i];
          tempArray[i] = temp;
          flag = true;
        }
      }
      this.user.numbers = tempArray;
    }
    this.user.numbers = this.user.numbers;
  }

  ngOnInit() {
  }

}
