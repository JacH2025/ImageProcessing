# CS 3500

- Jack Huang
- Sym Cunningham

### How to Use Image Processor GUI

the gui is able to do all the operations of the text based image processor. Use Command-line
argument -text or -t to use the text scripting mode.

#### menu

The menu at the top is where you go to execute commands.

The <b> Edit Image</b> menu is where you will find all the editing commands that can be done to an
image.

The <b> File </b> menu is where you go to save or load a file.

##### - User Input

The <b> load path </b> and <b> save path </b> text boxes are used input the save or load paths that
you would like to use with the <b> File </b> menu.

There are also <b>buttons</b> to open a pop-up file explorer which should be used to get filepaths
without having to type them out.

The <b> Image Name </b> and <b>New Name</b> text boxes are used to select which image the operations
are done on, and what they should be saved as. If you do not edit these fields commands will operate
on the image in view.

The <b>brighten</b> box is where you input the increment that you want to use for the brighten
command.

### How to Use Image Processor text

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
