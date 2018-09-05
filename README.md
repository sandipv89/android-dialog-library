# android-dialog-library
Android item list dialog
===================
Alert dialog for Android, select single item or multiple items as per your requirements.

## Setup
The simplest way to use SweetAlertDialog is to add the library as aar dependency to your build.

**Maven**

    <dependency>
	    <groupId>com.github.sandipv89</groupId>
	    <artifactId>android-dialog-library</artifactId>
	    <version>0.1.0</version>
	</dependency>

**Gradle**

    repositories {
        maven { url 'https://jitpack.io' }
    }

    dependencies {
        implementation 'com.github.sandipv89:android-dialog-library:0.1.0'
    }

## Usage

Implement OnResultListener.

You will get result in

    OnResult(List<Item>))

Just call this method with

1st

    activity

2nd

    List<Item>

3rd is for select multiple item or single item at a time

    true or false /* By default false single item selection */

4th listener

    onResultListener
    
    
Call this method with required parameters

          TabDialog.showDialogList(activity, items, false, listener);
