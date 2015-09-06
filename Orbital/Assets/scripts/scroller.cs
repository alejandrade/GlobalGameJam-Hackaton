using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class scroller : MonoBehaviour {

	ArrayList textObjects = new ArrayList();
	public GameObject textToAdd;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}



	public void addTextObject (string input, Color c, float moveAmount, Font f) {

		GameObject clone = Instantiate(textToAdd) as GameObject;
		
		clone.transform.SetParent(transform);

		Text newText = clone.GetComponent<Text>();


		
		newText.text = input;
		newText.font = f;
		newText.color = c;
		newText.fontSize = 16;
		newText.horizontalOverflow = HorizontalWrapMode.Wrap;
		newText.fontStyle = FontStyle.Normal;

		Rect rect = newText.GetPixelAdjustedRect();
		float newY = (rect.size.y/3) * (input.Length/53);

		///loop through all objects and move them up.
		if (textObjects.Count > 0) {
			for (int i=textObjects.Count-1; i >= 0; i--) {

				Transform t = (Transform)textObjects[i];

				//moveAmount += ((input.Length / 50) * newText.fontSize*2);


				moveAmount += newY;
				//print ();


				t.position = new Vector2(t.position.x, t.position.y + moveAmount);

				if (t.position.y > 700) {
					textObjects.Remove(t);
					Destroy(t.gameObject);
				}

			}
		}

		textObjects.Add(clone.transform);





		//find position to put new text.
		float addY = -180 + newY;
		Vector2 pos = new Vector2(transform.position.x - 15, transform.position.y + addY);
		clone.transform.position = pos;

		clone.SetActive(true);

	}

}
