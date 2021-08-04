# IPE
## Document Specification
[Android ML Project](/docs/Android_ML_Project.pdf)

## Steps
### 1. Downloading datasets
https://github.com/myleott/mnist_png (in png format). 30 images have been chosen to embed in Android Application located in assets/testset folder.

### 2. Machine Learning models (tflite output)
a) Exp01
https://colab.research.google.com/github/tensorflow/examples/blob/master/lite/codelabs/digit_classifier/ml/step2_train_ml_model.ipynb#scrollTo=Q_Z5yLxrwbpI

b) Exp02
https://colab.research.google.com/github/tensorflow/examples/blob/master/lite/codelabs/digit_classifier/ml/step7_improve_accuracy.ipynb#scrollTo=IPnP4xPbjqWi

### 3. Evaluation
* Exp 01 - Initial model
313/313 [==============================] - 5s 16ms/step - loss: 5.6524 - accuracy: 0.3342
[5.652446269989014, 0.334199994802475]

* Exp 02 - Improved model
313/313 [==============================] - 5s 15ms/step - loss: 0.2454 - accuracy: 0.9222
[0.2453950196504593, 0.9222000241279602]

### 4. Android implementation
Open Project in Android Studio using:
https://github.com/higa-daniel/ipe/tree/main/android

### 5. API level requirements
TBD
