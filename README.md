# CIS 436 - Project 3 - Cat App
## Authors: Onaopemipo Olagoke, Alex Parsons
### Description
An android application using Kotlin to get a better understanding of Volley, APIs, and JSON. We use https://thecatapi.com/ to get our cat information.

### Assignment
You are to create an application that allows the user to search for different cat breeds and to get
information about them. The application must use the Cat API (https://thecatapi.com/) – specifically read
the documentation (https://docs.thecatapi.com/) for information about how to get an API key, and how to
use the API. You can get the free API key and use it for this application without any issues - as long as you
don’t go wild and make more than 10,000 requests.
You will use the data returned as JSON from the Cat API to list the breeds of cats available through
the site in a drop-down (known as a Spinner in Android development). Once the breed is selected, the API
may be contacted again to obtain the image of the cat, if one exists. You should keep the spinner in a
fragment near the top of the app.
The fragment near the bottom of the app should contain an ImageView, and multiple widgets/views
for displaying text about the cat. In addition to displaying the image of the cat, you should show information
such as the name, temperament, and origin. The image view and other widgets for the breed information
could be kept inside a ScrollView.

### Dependencies
    glide: 
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.13.0")
    volley:
    implementation ("com.android.volley:volley:1.2.1")

### Screenshots
![](https://github.com/oo-ng/Project3_CatApp/blob/Parsons-Branch/Screenshots/Screenshot%202024-04-09%20at%204.37.30%E2%80%AFPM.png "Cat app screenshot")
![](https://github.com/oo-ng/Project3_CatApp/blob/Parsons-Branch/Screenshots/Screenshot%202024-04-09%20at%204.38.23%E2%80%AFPM.png "Cat app screenshot")
