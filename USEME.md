# CS 3500

- Jack Huang
- Sym Cunningham

### How to Use Image Processor

Start program. quit or q to exit the program. help or h to see a list of supported image operations,
and how to use them.

To use a command type the command and its arguments in the order seen bellow for each command. For
example:<br> 'load desktop/img.png img'<br> will load img into this image processor, and: <br>
'vertical-flip img img-flipped' <br> will add a new flipped version of img to the image processor.

Use flag -f or -file followed by a script file with commands inside to run a script. Program exits
at end of script.



<details><summary> Commands </summary>
<p>
load: imagePath, imageName <br>
save: imagePath, imageName <br>
horizontal-flip: imageName, destinationImageName <br>
vertical-flip: imageName, destinationImageName <br>
brighten: int increment, imageName, destinationImageName <br>
intensity-greyscale: imageName, destinationImageName <br>
luma-greyscale: imageName, destinationImageName <br>
value-greyscale: imageName, destinationImageName <br>
red-component: imageName, destinationImageName <br> 
blue-component: imageName, destinationImageName <br>
green-component: imageName, destinationImageName <br>
greyscale: imageName, destinationImageName <br>
blur: imageName, destinationImageName <br> 
sepia: imageName, destinationImageName <br>
sharpen: imageName, destinationImageName <br>
</p>
</details>
