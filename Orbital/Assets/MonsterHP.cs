using UnityEngine;
using System.Collections;

public class MonsterHP : MonoBehaviour {

	// Use this for initialization


	public int hp = 10;
	private GameObject father;
	input play;

	float damageTimer = 0.0f;
	float damageTime = 1.0f;



	void Start () {
		//father = GameObject.Find ("images");

		transform.position = new Vector3 (770, 315, 0);
//		transform.localScale.Scale = father.transform.localScale.Scale;
		 GameObject p = GameObject.Find("InputField");
		 play = p.GetComponent<input>();
		//player.currentRoom = this;
		//player.tryToGoTo(name);



	}
	
	// Update is called once per framessdfsdfsd
	void Update () {

		if (gameObject.GetActive() == true) {
			if (hp > 0) {
				damageTimer += Time.deltaTime;
				if (damageTimer >= damageTime) {
					doDamage();
					damageTimer = 0;
					damageTime = (Random.Range(5, 25) / 10.0f);

				}

			}

		}


	}

	void doDamage(){
		play.hp--;
		}

	public void takeDMG(int ammount){
		hp -= ammount;

		play.warningMessage ("Ahhhhhhhhh" + gameObject.GetComponent<item> ().name + "Has taken " + ammount.ToString () + " damage! ");


			if ( hp <= 0 ){
				transform.position = new Vector3 (-1000,-1000,0);
				gameObject.GetComponent<item>().name = gameObject.GetComponent<item>().name + "(DEAD)";
			}
		}
}
