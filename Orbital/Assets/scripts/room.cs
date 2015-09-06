using UnityEngine;
using System.Collections;
using System.Collections.Generic;

[RequireComponent (typeof (PhotonView))]

public class room : MonoBehaviour {

	public bool startingRoomForPlayer1 = false;
	public string name;
	public string enterMessage;
	public Transform[] rooms;
	public Transform[] items;


	public List<GameObject> _items;
	public List<GameObject> _rooms;

	public bool isLocked = false;
	public item itemToUnlock;
	public string lockedMessage;
	public ArrayList players = new ArrayList();

	public float timeSpent = 0;





	//PhotonView pv;

	// Use this for initialization
	void Start () {

		if (!PhotonNetwork.isNonMasterClientInRoom) {

			print ("room start");
			PhotonNetwork.sendRate = 40;
			PhotonNetwork.sendRateOnSerialize = 20;

			_items = new List<GameObject>();
			for (int i=0; i<items.Length; i++) {
				GameObject clone = null;
					clone = PhotonNetwork.Instantiate(items[i].name, new Vector3(0,0,0),Quaternion.identity, 0);
				
				if (clone) {
					print ("clone exists");
					_items.Add(clone);
				}
			}


			_rooms = new List<GameObject>();
			for (int i=0; i<rooms.Length; i++) {
				_rooms.Add(GameObject.Find(rooms[i].name + "(Clone)"));
			}

			PhotonNetwork.playerName = "Player 1 (" + name +")";

		}

		else {
				
			for (int i=0; i<items.Length; i++) {
				//PhotonView pv = PhotonView.Get(this);
				//pv.RPC("getItems", PhotonTargets.All, items[i].name);
				_items.Add(GameObject.Find(items[i].name + "(Clone)"));
			}


			for (int i=0; i<rooms.Length; i++) {
				_rooms.Add(GameObject.Find(rooms[i].name + "(Clone)"));
			}


			GameObject.Find("InputField").GetComponent<input>().playerName = "Player" + PhotonNetwork.countOfPlayers+1;

			PhotonNetwork.playerName = "Player" + PhotonNetwork.countOfPlayers + "(" + name +")"; 

		}


		if (startingRoomForPlayer1) {
			print ("go to room");
			GameObject p = GameObject.Find("InputField");
			input player = p.GetComponent<input>();
			//player.currentRoom = this;
			//player.tryToGoTo(name);
			player.gotoRoom(this);
		}

	}
	
	// Update is called once per frame
	void Update () {
		if (players.Count > 0) { ///there are players in the room.
			timeSpent += Time.deltaTime;
		}

		
		GameObject p = GameObject.Find("InputField");
		input player = p.GetComponent<input>();


		for (int i=0; i<_items.Count; i++) {


			if (_items[i] != null) {

				if (_items[i].GetComponent<MonsterHP>() != null)  { ///room has monster
					
					if (player.currentRoom == this) {

						_items[i].SetActive(true);

					}
					else {
						_items[i].SetActive(false);
					}

				}

			}

		}


	}


	
	[RPC]
	void sendChangeNotice() {



		/*
		_items.Clear();
		for (int i=0; i<items.Length; i++) {
			//PhotonView pv = PhotonView.Get(this);
			//pv.RPC("getItems", PhotonTargets.All, items[i].name);
			_items.Add(GameObject.Find(items[i].name + "(Clone)"));
		}

		Debug.Log("CHANGE NOTICE!!");
*/
	}



	void OnPhotonSerializeView(PhotonStream stream, PhotonMessageInfo info) {

		if (stream.isWriting == true) {
			
		}

		else {

		}

	}




}
