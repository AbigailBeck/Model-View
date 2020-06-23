package edu.cg.models;

import java.util.List;
import java.util.HashMap;

public interface IIntersectable {
	/**
	 * TODO: If you implement the bonus you will need to replace this method
	 * interface with something that supports the data structure you implemented.
	 * 
	 * @return a list of spheres that bound the object.
	 */
	public List<BoundingSphere> getBoundingSpheres();
	
	//public HashMap<String, List<BoundingSphere>> getBoundingSpheresinTree();
		
}
