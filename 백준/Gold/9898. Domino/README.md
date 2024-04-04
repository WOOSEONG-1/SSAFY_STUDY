# [Gold I] Domino - 9898 

[문제 링크](https://www.acmicpc.net/problem/9898) 

### 성능 요약

메모리: 16188 KB, 시간: 132 ms

### 분류

비트마스킹, 다이나믹 프로그래밍

### 제출 일자

2024년 2월 23일 11:07:04

### 문제 설명

<p>You are given a set of 2<i>n</i> dominos, where <i>n</i> < 1000 is an integer. The dominos have width 1 and length 2. It is possible to arrange these dominos in such a way that they form a 4 × <i>n</i> rectangle. For instance, in Figure 1, we have arranged 12 dominos to form a 4 × 6 rectangle.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/35708222-5e94-48f8-b769-6f71d10877b8/-/preview/" style="width: 171px; height: 120px;"></p>

<p style="text-align: center;">Figure 1: Example with <i>n</i> = 6.</p>

<p>In fact, where <i>n</i> > 1, there are several ways of arranging dominos to form a 4 × <i>n</i> rectangle. For instance, in Figure 2, you can see the 5 different ways of forming a 4 × 2 rectangle:</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/91bd45fa-782b-4d79-b011-2fc4db202635/-/preview/" style="width: 389px; height: 120px;"></p>

<p style="text-align: center;">Figure 2: The 5 different ways of forming a 4 × 2 rectangle.</p>

<p>We denote by <i>R</i><sub><i>n</i></sub> the number of different ways of forming a 4 × <i>n</i> rectangle with 2<i>n</i> dominos. For instance, <i>R</i><sub>2</sub> = 5, as you can see in Figure 2. As <i>R</i><sub><i>n</i></sub> can be quite large, even for small values of <i>n</i>, you are only asked to compute the last three digits of <i>R</i><sub><i>n</i></sub>. Your program should output the value of the last three digits of <i>R</i><sub><i>n</i></sub> without leading zeros. For instance, <i>R</i><sub>17</sub> = 26915305, so when <i>n</i> = 17, your program should output 305 (the last three digits). Another example, <i>R</i><sub>2</sub> = 5 (or 005), so when <i>n</i> = 2, your program should output 5. Should the last three digits be zeros for some <i>R</i><sub><i>n</i></sub>, your program should simply output 0.</p>

### 입력 

 <p>The input contains the integer <i>n</i>. Remember that <i>n</i> < 1000.</p>

### 출력 

 <p>You should write an integer giving the value of the last three digits of <i>R</i><sub><i>n</i></sub> without leading zeros in the output. (Note that the output value is simply the value <i>R</i><sub><i>n</i></sub> mod 1000; that is, the remainder of <i>R</i><sub><i>n</i></sub> divided by 1000.)</p>

