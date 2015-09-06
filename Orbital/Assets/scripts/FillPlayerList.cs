using UnityEngine;
using System.Collections;
using UnityEngine.UI; 
using System.Collections.Generic;

public class FillPlayerList : Photon.MonoBehaviour {

	// Use this for initialization


	Text instruction;
	room roomObj;

	public Font font;


	void Start () {
		instruction = GetComponent<Text>();
		instruction.color = Color.white;
		instruction.fontStyle = FontStyle.Bold;
		instruction.fontSize = 16;
		instruction.font = font;

	}
	// Update is called once per frame
	void FixedUpdate () {

		var playerListObj = PhotonNetwork.playerList;
		string playerListText = "Players in Game: \n---------------\n\n";

		for(int i=0; i< playerListObj.Length; i++){

			playerListText += playerListObj[i].name + "\n";
		}



		instruction.text = playerListText + "";
	
	
	}
}
