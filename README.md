# CS 3500

- Jack Huang
- Sym Cunningham

### ImageModel

<details><summary> ImageModel Impl </summary>
<p>
ImageModel Impl is our main image processor model. It has methods to load images into the model, and
save images that are currently in the model to the desired location.
ImageModel extends CommandImage which ensures that any implementation is able to apply ImageCommands
to images in the model. The model contains some methods that return information on the current 
image, so that the commands can access individual pixels, and know the height and width to iterate 
over.


</p>
</details>

<details><summary> Pixel Impl </summary>
<p>
We made an IPixel Interface to represent rgb pixels.
interface contains methods to get each pixel value, and to get the Luma, Value, and Intensity
of a pixel.

Our PixelImpl implements IPixel. This implementation uses values between 0 and 255 for each pixel
component value, any value below 0 is set to 0 and any value above 255 is set to 255.

</p>
</details>

### ImageController

<details><summary> Command Design Pattern </summary>
<p> ImageCommand interface is our command interface which is implemented by every command.
The commands that add effects to images such as greyscale or flip take an ImageModel, and are
constructed with an imageName and destination image name. They will search the model for an image
with imageName. If found, it will create a new image based on that image,  
and then the command will load the altered image into the model with the desired name.


</p>
</details>

<details><summary> Controller Impl </summary>
<p>
Our controller uses a command pattern, so the only built in commands are the help and quit commands.
The rest of the commands are stored in:
a &#40Map &lt String, Function &lt Scanner, ImageCommand&gt&gt commands&#41 &NewLine;
when the controller gets an input it checks if it is either quit or help, and then checks if the 
input is contained in the commands Map. If it is it will attempt to execute the command with the 
next user inputs. on success or failure it will alert the user, and then wait to accept the next
command.  



</p>
</details>

### How to Use Image Processor

Start program. quit or q to exit the program. help or h to see a list of supported image operations,
and how to use them.

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
</p>
</details>


<details><summary> test image src </summary>
<p>
http://web.eecs.utk.edu/~ssmit285/guide/img/index.html
</p>
</details>
