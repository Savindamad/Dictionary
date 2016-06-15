import java.awt.*;
import java.io.*;
import java.util.*; 
import javax.swing.*;
import java.awt.event.*;

class dic extends JFrame {
	
	//create java frame
	private JFrame dicGUI = new JFrame("Dictionary");
	//create all buttons, labels, buttons
	//intialize buttons
	private final JButton searchDefBtn = new JButton("Search");
	private final JButton searchSimBtn = new JButton("Search sim");
	private final JButton deleteBtn = new JButton("Delete");
	private final JButton clearBtn = new JButton("Clear");
	
	//intialize labels
	private final JLabel headding1 = new JLabel("My dictionary");
	private final JLabel headding2 = new JLabel("Add new word");
	private JLabel checkSuccess = new JLabel("");
	
	//intialize text fields
	private JTextField wordName = new HintTextField("Enter word");
	private JTextField wordNameAdd = new HintTextField("Enter word");
	private JTextField wordDefAdd = new HintTextField("Enter definition");
	private JTextField wordSimAdd = new HintTextField("Enter similar words");
	
	// initialize text area
	private JTextArea definitionAreaText = new JTextArea();

	//initialize panel
	private JPanel dicPanel = new JPanel();
	private JPanel addNewWordPanel = new JPanel();
	
	//create new binary search tree
	BinarySearchTree dictionary = new BinarySearchTree();

	dic(){
		this.insertData();
		this.createGUI();
	}

	public void createGUI(){
		//java frame 

		//set number of colums and rows of test field
		definitionAreaText.setColumns(20);
		definitionAreaText.setRows(5);

		javax.swing.GroupLayout dicPanelLayout = new javax.swing.GroupLayout(dicPanel);
        dicPanel.setLayout(dicPanelLayout);
        dicPanelLayout.setHorizontalGroup(
            dicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headding1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dicPanelLayout.createSequentialGroup()
                        .addGroup(dicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        	.addComponent(wordName)
                            .addComponent(definitionAreaText, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(dicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchDefBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchSimBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        dicPanelLayout.setVerticalGroup(
            dicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dicPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(headding1)
                .addGap(15, 15, 15)
                .addGroup(dicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wordName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchDefBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                	.addComponent(definitionAreaText)
                    .addGroup(dicPanelLayout.createSequentialGroup()
                        .addComponent(searchSimBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout dicLayout = new javax.swing.GroupLayout(dicGUI.getContentPane());
        dicGUI.getContentPane().setLayout(dicLayout);
        dicLayout.setHorizontalGroup(
            dicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dicLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dicLayout.setVerticalGroup(
            dicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dicLayout.createSequentialGroup()
                .addComponent(dicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );
        dicGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dicGUI.setLayout(dicLayout);
        dicGUI.pack();
        dicGUI.setLocationRelativeTo(null);
        dicGUI.setVisible(true);
	}

	public void insertData(){
		//insert data into binary search tree
		try{
			//read dictionary text file
            FileReader reader = new FileReader("input.txt");
 
            //buffer reader
            BufferedReader bufferedReader = new BufferedReader(reader);

			//intialize str1 and str2 as null
			String str1 = null; String str2 = null;

			//read input line by line
            while( (str1=bufferedReader.readLine()) != null ){
				int a = 0;
				//make string array, to add similar words
				String words[]=new String[10];
				StringTokenizer s1,s2;

 				//tokenize string by ','
 				s1=new StringTokenizer(str1,",");

				String word = s1.nextToken();
				String definition = s1.nextToken();
				String similar = s1.nextToken();
				//tokenize similar words
				s2=new StringTokenizer(similar," ");
				
				while (s2.hasMoreTokens()) 
				{		
					str2 = s2.nextToken();
					words[a++]=str2;
				}
				//get hash value of word
				int hashval = hashfunc(word);
				dictionary.insert(hashfunc(word),word,definition,words);
            }
			bufferedReader.close();
			reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
	}

	//hash function to hash word
	public static int hashfunc(String word){
		String hashval = "";
		for(int i = 0; i<word.length();i++){
			char c = word.charAt(i);
			if((int)c>96){
				hashval+=""+((int)c - 96);
			}
			else{
				hashval+=""+((int)c - 64);
			}
		}
		return Integer.parseInt(hashval);
	}

	//Search definition of given word
	public String findDefinition(String word){
		return dictionary.findDef(hashfunc(word));
	}

	//Seach similar words of given word
	public String[] findSimilarWords(String word){
		return dictionary.findSimilar(hashfunc(word));
	}

	//delete word
	public void delete(String word){
		if(dictionary.delete(hashfunc(word))){
			System.out.println("deleted successfully");
		}
		else{
			System.out.println("word not in dictionary");
		}
		
	}

	//update input text file
	public void update(){
		dictionary.writeData();
	}

	//main methord
	public static void main(String arg[]){
		//create new dic class
		dic d = new dic();
		System.out.println(d.findDefinition("dog"));
		d.delete("cow");
		System.out.println(d.findDefinition("cow"));
		System.out.println(d.findDefinition("cw"));
		d.update();
		
	}
}
class HintTextField extends JTextField implements FocusListener {

  private final String hint;
  private boolean showingHint;

  public HintTextField(final String hint) {
    super(hint);
    this.hint = hint;
    this.showingHint = true;
    super.addFocusListener(this);
  }

  @Override
  public void focusGained(FocusEvent e) {
    if(this.getText().isEmpty()) {
      super.setText("");
      showingHint = false;
    }
  }
  @Override
  public void focusLost(FocusEvent e) {
    if(this.getText().isEmpty()) {
      super.setText(hint);
      showingHint = true;
    }
  }

  @Override
  public String getText() {
    return showingHint ? "" : super.getText();
  }
}