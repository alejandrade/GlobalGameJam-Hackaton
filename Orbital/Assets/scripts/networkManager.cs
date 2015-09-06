using UnityEngine;
using System.Collections;

public class networkManager : MonoBehaviour {

	public GameObject item1;


	// Use this for initialization
	void Start () {
		Connect();
	}

	void Connect() {
		PhotonNetwork.ConnectUsingSettings("0.0.01 Alpha");

	}

	void OnGUI() {
		GUILayout.Label (PhotonNetwork.connectionStateDetailed.ToString());
	}

	void OnJoinedLobby() {
		PhotonNetwork.JoinRandomRoom();
	}

	void OnPhotonRandomJoinFailed() {
		Debug.Log("No Rooms, Creating room.");
		PhotonNetwork.CreateRoom("Cool Room");
	}

	void OnJoinedRoom() {
		Debug.Log("Joined Room");


		//PhotonNetwork.Instantiate("playCanvas", new Vector3(0,0,0),Quaternion.identity, 0);
		if (!PhotonNetwork.isNonMasterClientInRoom) {

			//Main Floor{
			PhotonNetwork.Instantiate("ship_cockpit", new Vector3(0,0,0),Quaternion.identity, 0);
			PhotonNetwork.Instantiate("ship_hallway", new Vector3(0,0,0),Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_infirmary", new Vector3(0,0,0), Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_cafeteria", new Vector3(0,0,0), Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_plants", new Vector3(0,0,0), Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_conference", new Vector3(0,0,0), Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_bathrooms", new Vector3(0,0,0), Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_storage", new Vector3(0,0,0), Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_engine", new Vector3(0,0,0), Quaternion.identity,0);
			//}



			//basement{
			PhotonNetwork.Instantiate("ship_turret", new Vector3(0,0,0),Quaternion.identity, 0);
			PhotonNetwork.Instantiate("ship_tools", new Vector3(0,0,0),Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_pantry", new Vector3(0,0,0), Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_cryo", new Vector3(0,0,0), Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_armory", new Vector3(0,0,0), Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_cargo", new Vector3(0,0,0), Quaternion.identity,0);
			PhotonNetwork.Instantiate("ship_hallway_bot", new Vector3(0,0,0), Quaternion.identity,0);

			//}


			//((MonoBehaviour)g.transform.FindChild("Test").GetComponent("room")).enabled = true;
			//((MonoBehaviour)g.transform.FindChild("Test2").GetComponent("room")).enabled = true;
		}

		


	}
	

}
