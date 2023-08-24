package prob2;

public class MusicPhone extends Phone {
	
	@Override
	public void execute ( String function ) {
	      if (function.equals("음악") ) {
	          playMusic();
	          return;
	      }

	      super.execute( function );
	}

	  private void playMusic(){
	    System.out.println("다운로드해서 음악재생");
	  }

}