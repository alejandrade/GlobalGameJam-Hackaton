using UnityEngine;
using System.Collections;
using UnityEngine.Rendering;
[RequireComponent(typeof(AudioSource))]
public class SoundSourceScript : MonoBehaviour {

	// Use this for initialization

	public  AudioClip hmm1;
	public  AudioClip hmm2;
	public  AudioClip hmm3;


	public static AudioClip explotion;


	


	public void  playHmm(){


	

		audio.PlayOneShot (hmm3);


	}



	public void playExplotion(){
		audio.PlayOneShot (explotion);


	}


}
