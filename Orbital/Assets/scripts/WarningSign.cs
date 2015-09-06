using UnityEngine;
using System.Collections;
using UnityEngine.UI; 
using System.Collections.Generic;


public class WarningSign : MonoBehaviour {

	// Use this for initialization

	System.DateTime startTime ;
	System.DateTime endTime ;


	void Start () {


		//endTime = System.DateTime.Now.AddSeconds (10);
	}
	
	
	void Update() {
		startTime = System.DateTime.Now;

		if(startTime>endTime){
			//PhotonNetwork.Destroy();

			//PhotonView photonView = PhotonView.Get(this);
			//photonView.RPC("DestroySign", PhotonTargets.All, this.gameObject);

		}

	}

	[RPC]
	void DestroySign(GameObject go)
	{
		//PhotonNetwork.Destroy(go);
	}
	

}
