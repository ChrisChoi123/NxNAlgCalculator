# NxNAlgCalculator
This program has two files that perform different things: one calculates the amount of algorithms (algs) in the average NxN blindfolded solve (algcount.py) and the other calculates the average target count for all the centres of any NxN cube larger than 3x3 (NxNCentres.java). 

### Instructions
To run the python file, go the directory of the file in the terminal or command prompt and type `python algcount.py` and you will met with some instructions. You can enter a value for N you wish to see the alg count for, and entering any non-nummber should be enough to exit the program. <br>To run the java file, go to the directory of the file in the terminal or command prompt and enter `javac NxNCentres.java` to compile, then enter `java NxNCentres` to run it. Again, you will be met with some instructions. To exit you enter 0, but to see the average targets for a particular NxN for the centres, you simply enter that N. 

### Background
This calculation was done assuming fixed buffer 3-style. For the corners and midges, I accounted for floating 2-twist and 2-flip. With these assumptions, Stanley Chapel arrived at 10.3 for the average alg count of a 3x3 solve. I also assumed the implementation of cycle breaks avoidance during centres (explained later in the README). For odd NxNs, the calculation was rather straightforward, but for even NxNs, I had to take into account the fact that you can orient it in an advantageous way due to lack of centre pieces. I found that a linear rational function is the best model for the behaviour of the even centres, which has an asymptote at infinity and a steep initial ascent.
I use 2 formulas, one for even and one for odd, where n is the number of layers.  

### The Formulas
Odd: ((n-3)/2)12+((n-3)/2)((n-3)/2+1)9.5854+4.12+6.18

Even: ((n-2)/2)12+(((n-2)/2)^2)(19.1771n - 47.9424)/(2(n - 2.1341)) + 4.12

### Work

#### 8/13/2020:
I came up with the 4.12/6.18 split for the 3x3 part of the solve (centres and midges) by analysing how Tom Nelson split up his corners and edges for the 3x3 part of the solve and I used the same ratio (2:3).

To derive the exact arctan expression, I used two very important pieces of information from Graham, mainly that for 4x4 centers, it's 16 targets on average, for 6x6 it's 18 targets on average, and it approaches 19.5 targets as N approaches infinity, keeping in mind that algs is just targets/2 because 3-style. This was very convenient because the arctan function had the exact property I wanted; going from 16 to 18 in just an increment of 2 was pretty large, and we had a finite limit as n approaches infinity. So since 4x4 was essentially our starting point, as any even number input below that would be meaningless, I wanted the inflection point of the inverse tan graph to be n = 4. This means that the x variable in arctan(x) would be replaced with n - 4. Additionally, since the desirable values were from 16 to 19.5, this meant that the range was 3.5 The positive half of the arctan function has a range of pi/2, so I would have to multiply the arctan function by (2/pi)(3.5) for the correct vertical dilation. However, we still need to add 16 because the inflection point of the parent arctan graph is at y = 0, and we want the target count to be 16 at the beginning when n = 4. Ok, that takes care of most of it, but we still need to use the 2nd point of data, that when n = 6 the target count is 18. This would be easy to do with a simple equation: 18 = 16 + (2(3.5)/pi)arctan(cn - 4), where c is an unknown constant we have to multiply n by to get the desired function. We want the equation to hold true when n is equal to 6, so we can solve for cn to find c. We get that cn is approximately 5.25396. Plugging in 6 for n, we get c = .87566. And finally, don't forget to divide by 2 in the final formula!

I was able to test my formula with Graham Siggin's predictions, which he so graciously supplied from 4x4 to 9x9. They were 24, 41, 64, 91, 123, 163 in that order and on average, my formulas were 3.79 times more geometrically accurate and 7.92 times more arithmetically accurate than Tom's formulas.

#### 8/14/20:
While browsing Facebook, it came to my attention that someone had already calculated the average number of targets for centres in 4x4-14x4 and 20x20. This is a much larger sample size than what I previously used for the varying function, which was just 4x4, 6x6, and an asymptote of 19.5. Additionally, I found that my arctan formula didn't even work properly in the first place, it was supposed to be 16 when n = 4, but it wasn't 16 because I forgot to distribute a constant out of both n and the -4 in the arctan's input. So I made the according change and I also shifted the arctan function up by .1 since Levi André Tallaksen (the guy who ran the simulations that calculated the average center targets) had the 4x4 estimate target count as 16.1 and 6x6 as 18.1 (so I assumed an asymptote of 19.6). I compared my even layered results with Levi's estimates and I found that mine differed from his significantly for 10x10 to 14x14. I wasn't satisfied so I tried using another function I knew that had a similar behaviour to arctan: an exponential. I started with a parent function of y = -e^(-x), and went through the same process as yesterday, giving me y = -3.5e^(-.42365(x-4)+19.6. While the 4x4 and 6x6 results were exactly the same as Levi's estimate, after all I derived it based on those two data points, the rest of the NxN data points were very different. It was then that I had an epiphany to try using a rational function. There are an infinite set of parent rational functions, since it is just any polynomial divided by another, so I searched up a rational regression calculator and came upon https://goodcalculators.com/rational-function-regression-calculator/. This calculator used a linear rational function, where both the numerator and denominator were linear functions of x. I was able to simply input all of the data points from Levi's calculation into the calculator and it gave me the equation (19.9933x - 51.5727)/(x - 2.2324).

Before I set out to test this equation's effectiveness in my formula, I merged my master branch with Stephen Huan's version of the file. He used a formula generator and used some fancy lambda stuff that I never learned, so thanks to him for that. It made my code a lot neater. After this, I was able to replace the arctan function I was using and played around with the new rational function. To make the calculations match as closely as possible to Graham's estimates, I ended up changing the coefficient in the numerator from 19.9933 to 19.75. This allowed all of my even estimates to be within .37, much more accurate than before. Additionally, I also improved my odd formula by changing the centre constant to 9.7, since it was previously 9.75 and I found that all of the odd calculations were overestimates from Graham's estimations. Overall, this improved the accuracy of my formulas by a factor of 2.15 geometrically and 1.46 arithmetically (now 8.13 and 11.54 respectively).

#### 8/16/20:
I finished learning 3-style wings, which I did because I wanted to experimentally verify Graham's estimate of 24 for the number of targets. Well, I did 4 wing only solves, all of which were exactly 24 targets. I stopped doing more because it was late at night and I wanted to watch some anime, but I trust Graham's estimate. I think it can also be mathematically justified. There are 24 wings, but minus the buffer there are 23 targets in a perfect run (where there are no solved wings and no cycle breaks). However, in solves, there can be solved wings and cycle breaks, but in my experience I found that cycle breaks are more common than solved pieces. Therefore, it makes sense that the average target count for wings should be larger than 23 but not by much, thus 24.

#### 8/17/20:
Today, I decided to experimentally verify the centre estimates by coding a program that simulates centres. I wasn't satisfied with Levi's results from 2 years ago, and since there were conflicts between Graham's estimate for the limit of even layered centers (19.5) and the limit predicted by rational regression using Levi's results (19.99), I wanted to reconcile the differences myself.

First, I used Levi's idea of representing the centres as numbers in an array, and I was able to code a function that created the centres. It was just arrays of 24 numbers, one number representing each colour, and the amount of arrays depending on the amount of orbits the NxN had, which I easily calculated using the expressions from each formula.

Then I had to figure out how to "solve" the centres. Each centre orbit would be solved the same way, so I worked on a function that went through each piece. I basically just did a simple 6 if-statement run where I systemically solved each piece by swapping it with the piece in the location it belonged in. However, I had to incorporate the avoidance of cycle breaking that Graham used. I asked a groupchat to explain how it worked and Daniel Lin basically put it like this: if 3 U-layer centres are already solved, you have to try to avoid any future U-layer centre. This makes sense because once you reach the last U layer centre, you are forced to cycle break since your buffer is now solved, but by avoiding the U layer centre, you can possibly reach every piece without cycle breaking. I incorporated this by having each while loop pass a position if it was the same colour as the U-layer and 3 U-layer pieces were already solved.

Then, I had to incorporate the fact that on even layer NxNs there are no fixed centres so you are free to orient the cube in any way that makes centre solving advantageous. This was the most tricky part, and I tried thinking of several ways of approaching it, like having 3 functions that rotate the cube in the x,y,z directions, but ultimately I had to ask Levi and he just told me to make a double array for every possible orientation. There are 24 orientations of the puzzles, and each orientation can be represented as a list of 6 numbers, each representing a side, just in different but valid orders. I did this manually while having a 4x4 in front of me that I used as a visual aid.

I wrote a function that was able to check every orientation of the puzzle, using some cleverly defined loops, and I was eventually able to fully incorporate this aspect into my code. It took a lot of debugging because I didn't realise the order that the solveCentre function was going through actually mattered. The final thing left to do was to implement an efficient way to test the simulations. I ended up going with a user input type of thing where the user inputted a NxN layer number and the main ran 100,000 trials. However, this was a smart tester because it took into account how many centre orbits the NxN had and adjusted the amount of trials of the entire puzzle so that the amount of centre orbits being tested was 100,000. So for example, 4x4 has a single orbit of x-centres so it would get tested (aka rescrambled) 100,000 times. 7x7 has 6 sets so it would only get tested 100,000/6 or 16,667 times. The testing procedure takes averages of the trials (each of which average each centre orbit target count) and outputs that overall average.

So, what are the results? Well, they're pretty surprising and not surprising at the same time. For one, they are significantly lower than the estimates Graham and Levi gave. For the odd-layered NxNs, the average centre target count was overall pretty consistent at 19.171 targets per centre orbit. Significantly lower than Graham's 19.5. For even-layered NxNs, they were less than what Levi calculated but still approached roughly the same limit as what my simulations gave for odd-layered NxNs: 19.177. This was calculated with the same regression calculator as before. Levi's estimates were:<br>
4x4: 16.1<br>
6x6: 18.1<br>
8x8: 18.8<br>
10x10: 19.1<br>
12x12: 19.3<br>
14x14: 19.4<br>
20x20: 19.6<br>
<br>
while mine were:<br>
4x4: 15.415<br>
6x6: 17.366<br>
8x8: 17.984<br>
10x10: 18.282<br>
12x12: 18.465<br>
14x14: 18.589<br>
20x20: 18.785<br>
I adjusted my python code to account for these new estimates and as a result, my calculations actually became less accurate in general (going from an average of 8.13 times more accurate than Tom's to an average of 3.94). However, I think this is perfectly fine, for a few reasons. First of all, Levi's predictions were most likely not done with avoiding cycle breaks, so it's expected that it's higher than what it should've been. Second of all, Graham's estimates aren't the end all be all; they are just estimates based on his own experience. The estimates that should be taken more seriously are his 4x4 and 5x5 estimates, because that's what he practises and logs the most. And indeed, my calculations became closer to his 4x4 and 5x5 estimates after adjusting for my centre experiments. This is good news for my formulas. I suspect that my formulas are probably more reliable and accurate than Graham's estimates, especially as N gets larger.

#### 10/5/20:
I explained my algorithm to Graham on youtube and he pointed out how I was mistaken in my undersanding of how cycle avoidance in centres worked and that I forgot to take into account how odd numbered targets for centres yield the same amount of algs as that target count plus 1. So I implemented those two into my code, and my results for the even layered alg counts became much closer to Graham's values, although the values for the odd-layered cubes deviated even more. What's more disappointing is that it seems like even though I completely changed the algorithm for solving centres, the only thing that seems to have an effect is the adding of an extra target for odd numbered target coubnts, which theoretically should add 0.5 if the algorithm is kept the same, because about half of all centre solves will be even and half will be odd, so adding 1 for odd will yield .5 on average, and that's pretty much what I observe in the results. The odd layered centre target counts all seem to be around 19.6649, almost exactly 0.5 greater than the avg for the previous algorithm. The new results for even layered cubes' centres are as follows: 
4x4: 15.9015
6x6: 17.8646
8x8: 18.4822
10x10: 18.7869
12x12: 18.9620
14x14: 19.0831
16x16: 19.1679
18x18: 19.2309
20x20: 19.2786
I don't know what I did wrong in my algorithm, or what I need to fix to make the odd-layered alg counts more accurate. I will have to continue thinking about this. 

