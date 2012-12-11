package cz.spalda2.objctojavacnv;

public class Objc2j {

	public static void main (String args[]) {
		if (args.length > 0) {
			new Converter().convert(args[0],args.length > 1 ? args[1] : args[0]);
		}
	}
}

//TODO
/*
 * turn [obj performSelectorInBackground...]
 * to       
 * new Thread() {
    public void run() {
     obj.selector(arg);
     }
    }.start();
   and [obj performSelector...]
   to running Runnable on the current thread 
   
*/
