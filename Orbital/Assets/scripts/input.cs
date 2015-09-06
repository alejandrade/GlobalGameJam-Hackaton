using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class input : MonoBehaviour {



	//player variables.
	public string playerName;
	public room currentRoom;
	public room previousRoom;
	public ArrayList inventory =  new ArrayList();

	public GameObject startingRoom;

	public int hp = 100;
	bool isDead = false;
	
	
	public GameObject soundManager;


	scroller scroller; //game object where all the text goes.. 


	//various text colors.
	Color ColorError;
	Color ColorAction;
	Color ColorNormal;
	Color ColorStory;
	Color ColorItems;


	
	public Font myFont;


	// Use this for initialization
	void Start () {
		scroller = GameObject.Find("TextScroller").GetComponent<scroller>();
		ColorError = new Color32(193, 57, 43, 255);
		ColorAction = new Color32(55, 243, 134, 255);
		ColorNormal = Color.white;
		ColorStory = new Color32(255, 139, 28,255);
		ColorItems = new Color32(177, 101, 206,255);


		//gotoRoom(startingRoom.GetComponent<room>());

		scroller.addTextObject("YOU HAVE JUST WOKEN FROM CRYOSTASIS", ColorStory, 0, myFont);
		//playerDied();
	}
	
	// Update is called once per frame
	void Update () {
		Text t = transform.FindChild("Text").GetComponent<Text>();
		//print (t.text);

		InputField i = GetComponent<InputField>();
		FillInventoryList.Inventory = inventory;



		if (hp <= 0) {
			if (!isDead) {
				playerDied();
			}
		}



	}




	public void done() {

		InputField i = GetComponent<InputField>();
		i.ActivateInputField();


		//create new text box.
		submitInput(i.text);

		
		i.text = "";
	}


	public void submitInput(string input) {


		if (input.Length > 0) {

			//print (input);

			scroller.addTextObject(playerName + " - " + input, ColorNormal, 30, myFont);




			/////check what type of input it is.

			///get the first word of the input.
			string[] words = input.Split(new char[] {' '}, System.StringSplitOptions.RemoveEmptyEntries);
			string firstWord = words[0];



			if (isDead) {

				if (firstWord.ToLower() == "retry") {

					scroller.addTextObject("YOU HAVE RE-AWOKEN FROM CRYOSTASIS!", ColorStory, 0, myFont);
					gotoRoom(GameObject.Find("ship_cryo(Clone)").GetComponent<room>());
					hp = 10;
					isDead = false;

				}

				else {

					reachedError("","That's not how you spell 'retry'!");

				}

			}



			else { ///you're not dead.


				/////------------------------there's only one word. check what one word possibilities there are.
				if (words.Length == 1) {  

					switch(firstWord.ToLower()) {

						///go back a room.
					case "leave":
					case "exit":
					case "return":
					case "back":
						goToPreviousRoom();
						break;
					

					default:
						reachedError(firstWord, "is not what we do now!");
						break;


					}

				}



				else { ///------------------------------more than one word.

					int checkNum = 1;
					string checkWord = words[checkNum];

					if (words.Length > 2) {
						if (checkWord.ToLower() == "the") {
							checkNum += 1;
							checkWord = words[checkNum];
						}
					}


					switch(firstWord.ToLower()) {
						
						///go back a room.
					case "leave":
					case "exit":
					case "return":
					case "back":
						switch (checkWord) {
						case "room":
						case "area":
						case "here":
						case "now":
							goToPreviousRoom();
							break;
						default :
							reachedError(input, "is not possible!");
							break;
						}
						break;






					case "goto":
					case "go":
					case "enter":
					case "walk":
					case "run":
					case "sprint":
					case "skip":

						if (checkWord.ToLower() == "back") {
							if (checkNum == words.Length-1) { //end of sentence.. go back..
								goToPreviousRoom();
								print ("go back");
								return;
							}
							checkNum += 1;
							checkWord = words[checkNum];
							//otherwise check the other words for consistencies.
						}
						if (checkWord.ToLower() == "to") {
							checkNum += 1;
							checkWord = words[checkNum];
							if (checkWord.ToLower() == "the") {
								checkNum += 1;
								checkWord = words[checkNum];
							}
						}
						if (checkWord.ToLower() == "door") {
							checkNum += 1;
							checkWord = words[checkNum];
							if (checkWord.ToLower() == "to") {
								checkNum += 1;
								checkWord = words[checkNum];
								if (checkWord.ToLower() == "the") {
									checkNum += 1;
									checkWord = words[checkNum]; 
								}
							}
						}
						//room name should be the rest of the sentence here.
						checkWord = "";
						for (int i=checkNum; i<words.Length; i++) {
							if (i>checkNum) checkWord += " ";
							checkWord += words[i];
							print (checkWord);
						}
						
						tryToGoTo(checkWord);
						break;











					
					case "inspect":
					case "observe":
					case "search":
					case "examine":
					case "view":
					case "study":
					case "look":
					case "check":
						if (checkWord.ToLower() == "at") {
							checkNum += 1;
							checkWord = words[checkNum];
						}
						else if (checkWord.ToLower() == "out" && firstWord.ToLower() == "check") {
							checkNum += 1;
							checkWord = words[checkNum];
						}
						if (checkWord.ToLower() == "the") {
							checkNum += 1;
							checkWord = words[checkNum];
						}

						checkWord = "";
						for (int i=checkNum; i<words.Length; i++) {
							if (i>checkNum) checkWord += " ";
							checkWord += words[i];
							print (checkWord);
						}
						
						tryToInspect(checkWord);
						break;

					


					
					case "take":
					case "acquire":
					case "attain":
					case "equip":
					case "collect":
					case "snag":
					case "grab":
					case "get":
					case "pick":
						if (checkWord.ToLower() == "up" && firstWord.ToLower() =="pick") {
							checkNum += 1;
							checkWord = words[checkNum];
						}
						if (checkWord.ToLower() == "the") {
							checkNum += 1;
							checkWord = words[checkNum];
						}

						checkWord = "";
						for (int i=checkNum; i<words.Length; i++) {
							if (i>checkNum) checkWord += " ";
							checkWord += words[i];
							print (checkWord);
						}
						
						tryToTake(checkWord);
						break;





					/*
					case "drop":
					case "discard":

					checkWord = "";
					for (int i=checkNum; i<words.Length; i++) {
						if (i>checkNum) checkWord += " ";
						checkWord += words[i];
						print (checkWord);
					}
					//print("Tried to Dropped blaster");
					
					tryToDrop(checkWord);
					break;
					
					*/






					case "use":

						if (checkWord == "blaster") {
							checkNum += 1;
							checkWord = words[checkNum];
							if (checkWord == "on") {
								checkNum += 1;
								checkWord = words[checkNum];
								
								if (checkWord == "firefly") {
									useBlasterWithFireFly();
									break;
								}
							}
						}

						if (checkWord == "the") {
							checkNum += 1;
							checkWord = words[checkNum];
						}
						checkNum += 1;
						checkWord = words[checkNum];
						print (checkWord);
						if (checkWord == "with" || checkWord == "on" || checkWord == "against" ||
						    checkWord == "toward" || checkWord == "at") {

							string word1 = words[checkNum-1];
							string word2 = "";

							if ((words.Length - 1) >= checkNum + 1) {
								if (words[checkNum+1] == "the") {
									checkNum += 1;
									checkWord = words[checkNum];
								}
							}

							if ((words.Length - 1) >= checkNum + 1) {
								word2 = words[checkNum+1];
							}
							tryToUseItems(word1, word2);

						}
						break;



					

					
					




					default:

						reachedError(input, "is not what we do now!");

						break;
						
					} /////////end the super long switch statement.

				}


			}


		}



	}








	/////// ------------------ possible outcomes
	/// 
	/// 
	/// 
	/// 
	/// 


	//////// ------- N A V I G A T I O N
	/// 
	/// 
	public void tryToGoTo(string checkWord) {

		room roomToGo = null;
		for (int i=0; i<currentRoom._rooms.Count; i++) {

			room r = currentRoom._rooms[i].GetComponent<room>();

			if (r.name.ToLower() == checkWord.ToLower()) {
				roomToGo = r;
			}

		}

		if (roomToGo != null) {

			if (roomToGo.isLocked) {
				reachedError("",roomToGo.lockedMessage);
			}
			else {
				gotoRoom(roomToGo);
			}

		}
		else {
			if (checkWord.ToLower() == currentRoom.name.ToLower()) {
				scroller.addTextObject("We are already in " + checkWord, ColorError, 30, myFont);
			}
			else {
				scroller.addTextObject(checkWord + " is not somewhere we do now!", ColorError, 30, myFont);
			}
		}

	}
	




	public void gotoRoom(room r) {
		if (previousRoom != null) {
			currentRoom.players.Remove(this);
		}

		previousRoom = currentRoom;

		scroller.addTextObject("--------------------------------", ColorStory, 50, myFont);

		scroller.addTextObject(r.enterMessage, ColorStory, 20, myFont);

		bool firefly = false;
		if (r._items.Count > 0) {
			print ("room has items");
			string itemString = "Room contains : ";
			for (int i=0; i<r._items.Count; i++) {
				item it = r._items[i].GetComponent<item>();
				itemString += " " + it.name;
				if (it.playerName.Length > 0) itemString += "("+it.playerName+")";
				if (i != r._items.Count-1) itemString += ",";
				
				if (it.name.ToLower() == "firefly") {
					firefly = true;
				}

			}
			scroller.addTextObject(itemString, ColorItems, 30, myFont);
		}
		else {
			print ("room has no items");
		}

		if (firefly) {
			warningMessage ("Oh no, it's a firefly! I hope I have my trusty blaster to shoot him on his face!'");
		}


		if (r._rooms.Count > 0) {

			string roomString = "Connected rooms : ";


			for (int i=0; i<r._rooms.Count; i++) {
				room it = r._rooms[i].GetComponent<room>();
				roomString += " " + it.name;
				if (i != r._rooms.Count-1) roomString += ",";

			}

			scroller.addTextObject(roomString, ColorItems, 30, myFont);

		}

		scroller.addTextObject("--------------------------------", ColorStory, 20, myFont);

		//print (r.enterMessage);

		currentRoom = r;

		currentRoom.players.Add(this);
		PhotonNetwork.playerName = playerName + "("+ currentRoom.name +")";
	}







	void goToPreviousRoom() {
		if (!previousRoom) {
			scroller.addTextObject("That's not somewhere we do now!", ColorError, 30, myFont);
			return;
		}
		gotoRoom(previousRoom);
	}





	//////// -------  I N S P E C T I O N
	/// 
	///


	void tryToInspect(string checkWord) {

		item itemToInspect = null;

		for (int i=0; i < currentRoom._items.Count; i++) {

			item curItem = currentRoom._items[i].GetComponent<item>();
			if (curItem.name.ToLower() == checkWord.ToLower()) {
				itemToInspect = curItem;

				break;
			}

		}
		if (itemToInspect == null) { //check your inventory.. for it.
			for (int i=0; i<inventory.Count; i++) {
				Transform curItem = (Transform)inventory[i];
				if (curItem.GetComponent<item>().name.ToLower() == checkWord.ToLower()) {
					itemToInspect = curItem.GetComponent<item>();


				}
			}
		}


		if (itemToInspect != null) {
			scroller.addTextObject(itemToInspect.inspectMessage, ColorAction, 30, myFont);
		}
		else {
			scroller.addTextObject(checkWord + " is not here!", ColorError, 30, myFont);
		}

		SoundSourceScript sound = soundManager.GetComponent<SoundSourceScript> ();
		sound.playHmm ();

	}







	void tryToTake(string checkWord) {

		item itemToTake = null;
		
		for (int i=0; i < currentRoom._items.Count; i++) {

			item curItem = currentRoom._items[i].GetComponent<item>();
			if (curItem.name.ToLower() == checkWord.ToLower()) { ///can't be held by player.
				itemToTake = curItem;
			}
			
		}
		if (itemToTake != null) {
			if (!itemToTake.canTake) { ///can't take the item...


			}
			else {//can take the item.....
				if (itemToTake.playerName.Length > 0) { /// a player has the item..
					if (itemToTake.playerName.ToLower() == playerName.ToLower()) { ///you are the player...
						scroller.addTextObject("You currently have the " + itemToTake.name, ColorError, 30, myFont);
					}
					else {
						scroller.addTextObject(itemToTake.playerName + " currently has the " + itemToTake.name, ColorError, 30, myFont);
					}
				}
				else {
					take (itemToTake);
					//itemToTake.player = this;
				}
			}
		}
		else {
			scroller.addTextObject(checkWord + " is not here!", ColorError, 30, myFont);
		}

	}






	/*

	void tryToDrop(string checkWord){
		item itemToDrop = null;
		
		for(int i=0; i < currentRoom._items.Count; i++) {
			
			item curItem = currentRoom._items[i].GetComponent<item>();
			if (curItem.name.ToLower() == checkWord.ToLower()) { ///can't be held by player.
				itemToDrop = curItem;
			}
			
		}
		
		
		
		if(inventory.Count==0){
			
			scroller.addTextObject ("You don't have any Items.", ColorError, 30, myFont);
		}
		
		for(int i=0; i<inventory.Count; i++){
			
			if(itemToDrop.playerName==playerName){
				scroller.addTextObject ("You dropped " + itemToDrop.name, ColorError, 30, myFont);
				
				drop (itemToDrop);
			}else{
				
				print("You don't have that item");
			}
		}
		
	}
*/




	void tryToUseItems(string item1, string item2) {

		print ("try to use items");

		item itemToUse = null;

		bool useItem1 = true;

		for (int i=0; i<inventory.Count; i++) {

			Transform it = (Transform)inventory[i];
			string itemname = it.GetComponent<item>().name;

			if (itemname.ToLower() == item1.ToLower()) {
				print ("item1");
				useItem1 = true;
				itemToUse = it.GetComponent<item>();
				break;
			}
			else if (itemname.ToLower() == item2.ToLower()) {
				print ("item2");
				useItem1 = false;
				itemToUse = it.GetComponent<item>();
				break;
			}


		}

		if (itemToUse != null) {

			print ("item to use is there");

			string itemToUseWithName = item1; ///chekc for item1.
			if (!useItem1) { ///check for item 2...
				itemToUseWithName = item2;
			}


			////check if the item is a room.
			room roomToUseWith = null;
			for (int i=0; i<currentRoom._rooms.Count; i++) {
				if (currentRoom._rooms[i].GetComponent<room>().name.ToLower() == item2.ToLower()) {
					roomToUseWith = currentRoom._rooms[i].GetComponent<room>();
					print ("foundroom");
					break;
				}
			}




			if (roomToUseWith == null) { ////it s not a room to use...

				item itemToUseWith = null;
				for (int i=0; i<currentRoom._items.Count; i++) {
					if (currentRoom._items[i].GetComponent<item>().name.ToLower() == itemToUseWithName.ToLower()) {
						itemToUseWith = currentRoom._items[i].GetComponent<item>();
						break;
					}
				}

				if (itemToUseWith == null) { ////it's not in the room so check your inventory...

					for (int i=0; i<inventory.Count; i++) {
						
						Transform it = (Transform)inventory[i];
						string itemname = it.GetComponent<item>().name;

						if (itemname.ToLower() == itemToUseWithName.ToLower()) {
							itemToUseWith = it.GetComponent<item>();
							break;
						}

					}

				}


				if (itemToUseWith != null) {

					///can they be used together...

					if (itemToUse.usedWith == itemToUseWith || itemToUseWith.usedWith == itemToUseWith) {

						useItems (itemToUse, itemToUseWith);

					}

					else {

						reachedError("", "The " + item1 + " and the " + item2 + " can't be used together!");

					}




				}

				else {

					reachedError("", "The " + item1 + " and / or the " + item2 + " are not here!");

				}



			}/////// end item 2 is not a room..


			else { ///item 2 is a room.

				//if (roomToUseWith.itemToUnlock == itemToUse) {

				useItems(itemToUse, roomToUseWith);

				//}

			}




		}

		else { ////you dont have the item you want to use...

			reachedError("", "You do not have the " + item1);

		}

	}

	












	void take(item itemToTake) {

		inventory.Add(itemToTake.transform);

		scroller.addTextObject("ATTAINED THE " + itemToTake.name, ColorAction, 30, myFont);

		//itemToTake.player = this;
		itemToTake.playerName = playerName;
	

		//PhotonView pv = PhotonView.Get(currentRoom);
		//pv.RPC("sendChangeNotice", PhotonTargets.All, null);

	}

	/*
	void drop(item itemToDrop) {
		
		inventory.Remove(itemToDrop.transform);
		
		scroller.addTextObject("Dropped THE " + itemToDrop.name, ColorAction, 30, myFont);
		
		//itemToTake.player = this;
		itemToDrop.playerName = "";
		itemToDrop.player = null;
		itemToDrop.used = false;

		
		PhotonView pv = PhotonView.Get(currentRoom);
		pv.RPC("sendChangeNotice", PhotonTargets.All, null);
		
	}


	*/








	void useItems(item itemToUse, item itemToUseWith) {

		string itemToUseName = itemToUse.name.ToLower();
		string itemToUseWithName = itemToUseWith.name.ToLower();


		if ((itemToUseName == "blaster" && itemToUseWithName == "screwdriver") ||
		    (itemToUseWithName == "blaster" && itemToUseName == "screwdriver"))
		{


			item blaster = null;
			if (itemToUseWithName == "blaster") {
				blaster = itemToUseWith;
			}
			else {
				blaster = itemToUse;
			}



			if (blaster.strength >= 8) {
				reachedError("", "The blaster can not be tuned up any further!");
			}
			else {
				blaster.strength +=1;
				usedItem("You've tuned up the blaster!");
			}


		}




		else if ((itemToUseName == "blaster" && itemToUseWithName == "firefly") ||
		    (itemToUseWithName == "blaster" && itemToUseName == "firefly"))
		{
			item firefly = itemToUse;
			item blaster = itemToUseWith;
			if (itemToUseWithName == "firefly") {
				firefly = itemToUseWith;
				blaster = itemToUse;
			}

			firefly.GetComponent<MonsterHP>().takeDMG(blaster.strength);

			mainCamera cam = Camera.main.GetComponent<mainCamera>();
			cam.screenShake(cam.screenShakeAmt_Sml);

			
		}





		else if ((itemToUseName == "keycard" && itemToUseWithName == "console") ||
		         (itemToUseWithName == "keycard" && itemToUseName == "console"))
		{
			
			usedItem("You've upgraded the keycard!");
			
		}

		else if ((itemToUseName == "blaster" && itemToUseWithName == "screwdriver") ||
		         (itemToUseWithName == "blaster" && itemToUseName == "screwdriver"))
		{
			
			
			
		}

		else if ((itemToUseName == "blaster" && itemToUseWithName == "screwdriver") ||
		         (itemToUseWithName == "blaster" && itemToUseName == "screwdriver"))
		{
			
			
			
		}

		else if ((itemToUseName == "blaster" && itemToUseWithName == "screwdriver") ||
		         (itemToUseWithName == "blaster" && itemToUseName == "screwdriver"))
		{
			
			
			
		}

		else if ((itemToUseName == "blaster" && itemToUseWithName == "screwdriver") ||
		         (itemToUseWithName == "blaster" && itemToUseName == "screwdriver"))
		{
			
			
			
		}

		else if ((itemToUseName == "blaster" && itemToUseWithName == "screwdriver") ||
		         (itemToUseWithName == "blaster" && itemToUseName == "screwdriver"))
		{
			
			
			
		}

		else if ((itemToUseName == "blaster" && itemToUseWithName == "screwdriver") ||
		         (itemToUseWithName == "blaster" && itemToUseName == "screwdriver"))
		{
			
			
			
		}

		else if ((itemToUseName == "blaster" && itemToUseWithName == "screwdriver") ||
		         (itemToUseWithName == "blaster" && itemToUseName == "screwdriver"))
		{
			
			
			
		}

		else if ((itemToUseName == "blaster" && itemToUseWithName == "screwdriver") ||
		         (itemToUseWithName == "blaster" && itemToUseName == "screwdriver"))
		{
			
			
			
		}


	}







	void useItems(item itemToUse, room roomToUseWith) {


		//if (itemToUse == roomToUseWith.itemToUnlock) {
			roomToUseWith.isLocked = false;
			scroller.addTextObject(roomToUseWith.name + " is has been unlocked!", ColorAction, 30, myFont);
		//}


	}






	void reachedError(string input, string error) {
		scroller.addTextObject("--------------------------------", ColorError, 20, myFont);
		scroller.addTextObject(input + " " + error, ColorError, 30, myFont);
		scroller.addTextObject("--------------------------------", ColorError, 30, myFont);
		//print ("unknown answer");

	}




	void usedItem(string message) {
		scroller.addTextObject("--------------------------------", ColorError, 20, myFont);
		scroller.addTextObject(message, ColorAction, 30, myFont);
		scroller.addTextObject("--------------------------------", ColorError, 30, myFont);
		//print ("unknown answer");
		
	}




	public void warningMessage(string message) {
		scroller.addTextObject("--------------------------------", ColorError, 20, myFont);
		scroller.addTextObject(message, ColorStory, 30, myFont);
		scroller.addTextObject("--------------------------------", ColorError, 30, myFont);
		//print ("unknown answer");
		
	}




	void useBlasterWithFireFly() {

		item firefly = GameObject.Find("Firefly(Clone)").GetComponent<item>();
		item blaster = GameObject.Find("blaster(Clone)").GetComponent<item>();
		
		firefly.GetComponent<MonsterHP>().takeDMG(blaster.strength);
		
		mainCamera cam = Camera.main.GetComponent<mainCamera>();
		cam.screenShake(cam.screenShakeAmt_Sml);

	}


	public void playerDied() {


		warningMessage("YOU HAVE DIED!");

		isDead = true;

		scroller.addTextObject("type 'retry' to spawn.", ColorStory, 50, myFont);




	}





}
