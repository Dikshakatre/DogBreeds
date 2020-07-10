# DogBreeds

I. Problem definition:
Showcase list of Dog Breeds from given api and show at most 3 random images of each breed on the homescreen. Also, show all images of dog breeds on a separate screen.
II. Architecture:
a. Followed MVVM architecture for project. Used ViewModels like DogBreedsViewModel, DogBreedImageViewModel for both activities i.e DogBreedsActivity and DogBreedImagesActivity respectively.
b. Used Data Binding Library to bind UI components of layouts to data sources in the app using a declarative format.
III. Challenges faced:
Seamless scrolling of recyclerviews while loading images both on the homescreen and on the screen to show all images for a breed.
IV. Solutions:
I used two nested model classes to load images on the home screen. Also, binded the adapter view with the DogBreed data model and added API call in it.
V. Directions to use application:
1. HomeScreen:Showsthelistofalldogbreedsandupto3random images of each one.
2. Userscanrefreshthelistbyclickingthe“Refresh”buttonatthe top.
3. Users can also see all images available of a particular breed of Dog clicking “View More Images” link.
4. TheAppalsosupportsbothlandscapeandportraitscreen orientations
VI. Libraries used:
1. GlideforloadingimagesinImageview.
2. RetrofitfornetworkcallwithGsonparsingtheResponseJson. 3. JUnitforwritingunittestcasesandMockitoformockingdata
classes.
VII. API Called:
a. For fetching all dog breed names https://dog.ceo/api/breeds/list/all
b. For fetching 3 random images of every breed https://dog.ceo/api/breeds/image/random/3
C. For fetching all images of a breed https://dog.ceo/api/breed/hound/images
