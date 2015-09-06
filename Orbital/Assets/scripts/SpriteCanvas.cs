using UnityEngine;
using System.Collections;

public class SpriteCanvas : MonoBehaviour {

	// Use this for initialization

	private input inputObj;
	public SpriteRenderer spriteRenderer;

	public Sprite hallway;
	public Sprite cafeteria;
	public Sprite infirmary;
	public Sprite plants;
	public Sprite conference;
	public Sprite bathroom;
	public Sprite storage;
	public Sprite engine;
	public Sprite turret;
	public Sprite tools;
	public Sprite pantry;
	public Sprite cryo;
	public Sprite armory;
	public Sprite cargo;
	public Sprite cockpit;

	

	void Start () {

				GameObject p = GameObject.Find ("InputField");
				inputObj = p.GetComponent<input> ();
		//sprite =  this.gameObject.GetComponent<SpriteRenderer>();

			
		}
	// Update is called once per frame
	void Update () {





		if (inputObj.currentRoom) {



			switch(inputObj.currentRoom.name){

			case "Hallway":
				spriteRenderer.sprite = hallway;
				break;

			case "Turret":

				spriteRenderer.sprite = turret;
				break;

			case "Cafeteria":
				spriteRenderer.sprite = cafeteria;
				break;
			case "Cargo":
				spriteRenderer.sprite = cargo;
				break;
				
			case "Cockpit":
				spriteRenderer.sprite = cockpit;
				break;
			case "Cryogenics":
				spriteRenderer.sprite = cryo;
				break;
			case "Engine":

				spriteRenderer.sprite = engine;
				break;
				
			case "Infirmary":
				spriteRenderer.sprite = infirmary;
				break;
				
			case "Pantry":
				spriteRenderer.sprite = pantry;
				break;
				
			case "Biodome":
				spriteRenderer.sprite = plants;
				break;
			case "Bathroom":
				spriteRenderer.sprite = bathroom;
				break;
				
			case "Armory":
				spriteRenderer.sprite = armory;
				break;

			case "Utilities":
				spriteRenderer.sprite = tools;
				break;
				
			case "Storage":
				spriteRenderer.sprite = storage;
				break;





			default:
				break;
			}


		}

	
	}
}
