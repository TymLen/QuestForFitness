package com.ict376.tym.databaseview;



import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class CheckinTextBoxFrag extends Fragment {
    TextView mTextText;
    String[] location = {"the village", "the backstreets", "a pub", "the inn", "the adventurer guild", "the countryside", "a well travelled road",
            "the town square", "the general store", "the local brothel", "the local church", "a graveyard", "a seedy alley"};
    String[] questGiver = {"a beggar", "the mayor", "the Princess", "the Prince", "a talking pig", "a talking goat", "the King", "a brigand", "a drunkard",
    "your cousin", "the innkeeper", "a wandering bard", "your mum", "a gentleman of shadiness", "a traveling monk"};
    String [] item = {"crown", "potato", "carrot", "sword", "mace", "stick", "goblet", "ring", "book", "underwear", "horse shoe", "puppy", "kitten", "clothes", "violin"};
    String [] enemy = {"goblin", "hobgoblin", "ghost", "lizardman", "giant rat", "skeleton", "dragon", "centaur", "slime", "zombie", "bandit", "lich", "robot", "alien", "drunkard"
    , "beggar", "vampire", "kobold", "giant", "cyclops", "bear", "panther", "bird"};
    String [] adjEnemy = {"scary", "holy", "smelly", "unholy", "one armed", "fat", "legendary", "famous", "ancient", "brutish", "smart-looking", "depressed", "cheerful", "tiny", "inconvenient"};
    String [] adjItem = {"holy", "unholy", "legendary", "famous", "ancient", "fashionable", "shiny", "broken", "smelly", "moldy", "dirty", "mundane", "royal", };
    String [] strAction = {"hit", "bear hug", "squeeze", "wrestle", "bench press", "flex at", "smash", "punch", "arm wrestle"};
    String [] dexAction = {"dance with", "dodge around", "run circles around", "squat challenge", "stealth around", "kick", "trip", "jump over"};
    String setItem, setEnemy, setAdjItem, setAdjEnemy, setLocation, setQuestGiver;
    String storyText ="";
    String battleText = "";
    String endText = "";
    String story = "";
    String action = "";
    final Handler handler = new Handler();
    int j = 0;
    float weight = 0;


    public CheckinTextBoxFrag() {
        // Required empty public constructor
    }
    public static CheckinTextBoxFrag NewInstance(float inWeight, String inAction){
        CheckinTextBoxFrag fragment = new CheckinTextBoxFrag();
        Bundle args = new Bundle();
        args.putString("action", inAction);
        args.putFloat("weight", inWeight);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null){
            setItem = savedInstanceState.getString("setItem");
            setEnemy = savedInstanceState.getString("setEnemy");
            setAdjItem = savedInstanceState.getString("setAdjItem");
            setAdjEnemy = savedInstanceState.getString("setAdjEnemy");
            setLocation = savedInstanceState.getString("setLocation");
            setQuestGiver = savedInstanceState.getString("setQuestGiver");
            storyText = savedInstanceState.getString("storyText");
            battleText = savedInstanceState.getString("battleText");
            endText = savedInstanceState.getString("endText");
            story = savedInstanceState.getString("story");
            weight = savedInstanceState.getFloat("weight", 0);
            action = savedInstanceState.getString("action");

        }else{
            setItem = item[(int)(Math.random() *item.length)];
            setAdjItem = adjItem[(int)(Math.random() *adjItem.length)];
            setQuestGiver = questGiver[(int)(Math.random() *questGiver.length)];
            setLocation = location[(int)(Math.random() *location.length)];
            setEnemy = enemy[(int)(Math.random() *enemy.length)];
            setAdjEnemy = adjEnemy[(int)(Math.random() *adjEnemy.length)];
            Intent intent = new Intent(getContext(), BGMusic.class);
            intent.putExtra("rawID", R.raw.breakingin);
            getContext().startService(intent);
        }
        return inflater.inflate(R.layout.fragment_text_box, container, false);
    }
    @Override
    public void onPause(){
        super.onPause();
        if(!getActivity().isChangingConfigurations()){
            Intent intent = new Intent(getContext(), BGMusic.class);
            getContext().stopService(intent);
        }
    }
    public void stopHandler(){
        handler.removeMessages(0);
    }

    @Override
    public void onResume(){
        super.onResume();
        Intent intent = new Intent(getContext(), BGMusic.class);
        intent.putExtra("rawID", R.raw.breakingin);
        getContext().startService(intent);
        mTextText.setText(story);
    }

    public void setWeight(float inWeight){
        weight = inWeight;
    }
    public void setAction(String inAction){
        action = inAction;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        if(getView() !=null){
            mTextText = getView().findViewById(R.id.TextText);
            Typeface tf = Typeface.createFromAsset(getContext().getApplicationContext().getAssets(), "fonts/SHPinscher-Regular.otf"); //https://stackoverflow.com/questions/27588965/how-to-use-custom-font-in-a-project-written-in-android-studio
            mTextText.setTypeface(tf);
            if(story.equals("")){
                mTextText.setText("");
                createStory();
            }else{
                mTextText.setText(story);
            }

        }
    }

    public void createStory(){
        mTextText.setText("");
        story = "You wander around "+setLocation+" when you come across "+setQuestGiver+ " that tells you the location of the " +
                ""+setAdjItem+ " "+setItem+" which must be reclaimed! \nYou begin gathering supplies but all good adventurers manage their weight."+
                "\nHow much do you weigh adventurer?";

        handler.post(new Runnable(){
        @Override
        public void run(){
            String[] storyBits = story.split("");
            storyText = storyText + storyBits[j];
            mTextText.setText(storyText);
            j++;
            if(j <storyBits.length){
                handler.postDelayed(this, 10);
            }
        }
        });
    }
    public void Battle(){
        mTextText.setText("");
        story = "Following the instructions you received from "+setQuestGiver+", you arrive at the location of the "+setAdjItem+" "+setItem+ " but in your way is a " +setAdjEnemy+
                " "+setEnemy+ " standing in your way.\nHow do you get past?";
        j = 0;
        stopHandler();
        handler.post(new Runnable(){
            @Override
            public void run(){
                String[] storyBits = story.split("");
                battleText = battleText + storyBits[j];
                mTextText.setText(battleText);
                j++;
                if(j <storyBits.length){
                    handler.postDelayed(this, 10);
                }
            }
        });
        mTextText.setText(story);
    }
    public void Victory(){
        mTextText.setText("");
        String verb;
        j = 0;
        stopHandler();
        if(action.equals("Use Upper Body")){
            String setStr = strAction[(int)(Math.random() *strAction.length)];
            verb = "upper body strength.";
            story = "You "+setStr+" the "+setAdjEnemy+ " "+setEnemy+" and it died. You step over the corpse and claim the "+setAdjItem +" "+setItem+" and with it also claim another victory on the Quest for Fitness.";
        }
        else if(action.equals("Use Lower Body")){
            verb = "lower body strength.";
            String setDex = dexAction[(int)(Math.random() *dexAction.length)];
            story = "You "+setDex+" the "+setAdjEnemy+ " "+setEnemy+" and it died. You step over the corpse and claim the "+setAdjItem +" "+setItem+" and with it also claim another victory on the Quest for Fitness.";
        }
        else{
            verb = "a well earned rest.";
            story = "You decide that the "+setAdjItem +" "+setItem+" isn't worth fighting a "+setAdjEnemy+ " "+setEnemy+" for and return to the inn to prepare for your next adventure";
        }
        story = story +"\n\n"+"You weighed "+weight+" and completed the adventure using "+verb+"\nRecord Adventure?";
        handler.post(new Runnable(){
            @Override
            public void run(){
                String[] storyBits = story.split("");
                endText = endText + storyBits[j];
                mTextText.setText(endText);
                j++;
                if(j <storyBits.length){
                    handler.postDelayed(this, 10);
                }
            }
        });
        mTextText.setText(story);
    }
    @Override
    public void onSaveInstanceState (Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putString("setItem", setItem);
        bundle.putString("setEnemy", setEnemy);
        bundle.putString("setAdjItem", setAdjItem);
        bundle.putString("setAdjEnemy", setAdjEnemy);
        bundle.putString("setLocation", setLocation);
        bundle.putString("setQuestGiver", setQuestGiver);
        bundle.putString("storyText", storyText);
        bundle.putString("battleText", battleText);
        bundle.putString("endText", endText);
        bundle.putString("story", story);
        bundle.putFloat("weight", weight);
        bundle.putString("action", action);
        stopHandler();
    }
}
