using UnityEngine;
using System.Collections;

[RequireComponent (typeof (PhotonView))]

public class item : Photon.MonoBehaviour {

	public string name;
	public string inspectMessage; //when inspecting the item. what it says.
	public int strength; //damage if thrown at something.
	public bool used = false; //whether the item it done being used.
	public input player = null; //which player is holding the item.
	public string playerName = "";
	public bool canTake = true; //whether the player can pick up the item or if it has another use.
	public item usedWith = null; //an item that you need to use this item.. assuming this item is static.




	// Use this for initialization
	void Start () {
		PhotonNetwork.sendRate = 40;
		PhotonNetwork.sendRateOnSerialize = 20;
	}
	
	// Update is called once per frame
	void Update () {
		//print ("blaster exists");




	}





	void OnPhotonSerializeView(PhotonStream stream, PhotonMessageInfo info) {
		
		if (stream.isWriting) {
			print ("writing player info");
			stream.SendNext(playerName);
		}
		
		else {
			print ("reading player info");
			playerName = (string)stream.ReceiveNext();

		}
		
	}


	
}
