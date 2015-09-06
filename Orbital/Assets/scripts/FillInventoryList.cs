using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI; 


public class FillInventoryList : MonoBehaviour {

	// Use this for initialization
	
	Text instruction;
	room roomObj;
	Transform temp;
	public static ArrayList Inventory = new ArrayList();

	public Font font;
	
	
	void Start () {

		instruction = GetComponent<Text>();
		instruction.color = Color.white;
		instruction.fontStyle = FontStyle.Bold;
		instruction.fontSize = 16;
		instruction.font = font;
		
		
	}
	// Update is called once per frame
	void Update () {
		string InventoryListText = "Inventory\n---------------\n\n";
		//if (Inventory) {
			for(int i=0; i< Inventory.Count; i++){
				Transform temp =  (Transform)Inventory[i];
				InventoryListText += temp.name.Replace("(Clone)","") + "\n";
			}
		//}

		instruction.text = InventoryListText + "";
	}



}
