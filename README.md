# android-multiitem-select-dialog
Android multiitem select dialog
===================
Alert dialog for Android, select single item or multiple items as per your requirements.

## Setup
The simplest way to use this library is to add the library as aar dependency to your build.

**Maven**

    <dependency>
	    <groupId>com.github.sandipv89</groupId>
	    <artifactId>android-multiitem-select-dialog</artifactId>
	    <version>0.1.0</version>
	</dependency>

**Gradle**

    repositories {
        maven { url 'https://jitpack.io' }
    }

    dependencies {
        implementation 'com.github.sandipv89:android-multiitem-select-dialog:0.1.0'
    }


## Changes in version 0.2.0

now you can change background color of selected item and normal item

You just need to do is to pass color int as given below

	TabDialog.showDialogList(activity, items, false, listener, R.color.grey_300, R.color.white);
	
5th parameter

	R.color.grey_300 //selected item backgrond
	
6th parameter

	R.color.white //normal item color


## Usage

Import following

	import in.tabdevelopers.mylibrary.Item;
	import in.tabdevelopers.mylibrary.OnResultListener;
	import in.tabdevelopers.mylibrary.TabDialog;
	

Implement OnResultListener.

You will get result in

	OnResult(List<Item>))

Call this method with required parameters

	TabDialog.showDialogList(activity, items, false, listener);

Just call this method with

1st

	activity

2nd

	List<Item>

3rd is for select multiple item or single item at a time

	true or false /* By default false single item selection */

4th listener

	onResultListener


## Example

	import in.tabdevelopers.mylibrary.Item;
	import in.tabdevelopers.mylibrary.OnResultListener;
	import in.tabdevelopers.mylibrary.TabDialog;

	public class MainActivity extends AppCompatActivity implements OnResultListener {
	    private List<Item> items = new ArrayList<>();
	    private AppCompatActivity activity = this;
	    private OnResultListener listener;

	@Override
    	protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_main);
        	listener = this;

	        prepareItems();


	        TabDialog.showDialogList(activity, items, false, listener);

	    }

	    private void prepareItems() {
        	for (int i = 0; i < 10; i++) {
	            	Item item = new Item();
        	    	item.setTitle("Title" + i);
            		item.setSubTitle("SubTitle" + i);
            		items.add(item);
	        	}
    	}

	//selected items list from dialog

    	@Override
    	public void OnResult(List<Item> list) {

	    }
    }

