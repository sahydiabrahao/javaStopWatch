import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopWatch implements ActionListener {
	
	JFrame 		frame 		= 	new JFrame();
	JButton 	startButton = 	new JButton("START");
	JButton 	resetButton = 	new JButton("RESET");
	JLabel		timeLabel	= 	new JLabel();
	
	int			elapsedTime	= 	0;
	int			seconds 	= 	0;
	int			minutes 	= 	0;
	int			hours	 	= 	0;
	boolean 	started 	= 	false;
	
	String		seconds_string 	= String.format("%02d", seconds);		//02 DOIS ZEROS
	String		minutes_string 	= String.format("%02d", minutes);		//02 DOIS ZEROS
	String		hours_string 	= String.format("%02d", hours);			//02 DOIS ZEROS
	
//VARIAVEL TIMER E SUA FUNÇÃO A CADA 1SEC...................................................	
	Timer timer = new Timer(1000, new ActionListener() {		// ATUALIZA A CADA 1000 miliSEC = 1SEC

		@Override //elapsed = TEMPO DECORRIDO EM SEGUNDOS(TD)
		public void actionPerformed(ActionEvent e) {
			elapsedTime = elapsedTime + 1000;					//+1S A CADA 1S
			hours = (elapsedTime/3600000);						// TD/MiliSEG EM 1H		= X H
			minutes = (elapsedTime/60000) % 60;					// TD/MiliSEG EM 1MIN	= X MIN
			seconds = (elapsedTime/1000) % 60;					// TD/MiliSEG EM 1SEG	= X SEG
			
			seconds_string 	= String.format("%02d", seconds);	//ATUALIZA
			minutes_string 	= String.format("%02d", minutes);	//ATUALIZA
			hours_string 	= String.format("%02d", hours);		//ATUALIZA
			
			timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string); //RÓTULO hh:mm:ss
		}					
	});				
	
//CONSTRUTOR.............................................................................
	StopWatch(){
		//TEMPO...........................................................................
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string); //RÓTULO hh:mm:ss
		timeLabel.setBounds(5,5,200,100);						//COORDENADA X,Y TAMANHO ,X,Y
		timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));//FONTE
		timeLabel.setBorder(BorderFactory.createBevelBorder(1)); //BORDA
		timeLabel.setOpaque(true);								 //OPACIDADE 1
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		//BOTÃO...........................................................................
		startButton.setBounds(5,105,100,50);					//COORDENADA X,Y TAMANHO ,X,Y
		startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));//FONTE
		startButton.setFocusable(false);						//TIRA BORDA INTERNA
		startButton.addActionListener(this);					//ADICIONA FUNÇÃO
	
		resetButton.setBounds(105,105,100,50);					//COORDENADA X,Y TAMANHO ,X,Y
		resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));//FONTE
		resetButton.setFocusable(false);						//TIRA BORDA INTERNA
		resetButton.addActionListener(this);					//ADICIONA FUNÇÃO
		
		//ADICIONA A TELA..................................................................
		frame.add(startButton);									//ADICIONA BOTÃO START
		frame.add(resetButton);									//ADICIONA BOTÃO RESET
		frame.add(timeLabel);									//ADICIONA RÓTULO hh:mm:ss
		
		//TELA............................................................................
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//BOTÃO DE FECHAR
		frame.setTitle("Crônometro");							//TÍTULO	
		frame.setSize(230, 200);								//DIMENSÃO
		frame.setResizable(false); 								//TAMANHO FIXO
		frame.setLayout(null);									//SEM LAYOUT
		frame.setVisible(true);									//VISIBILIDADE		
	}
	//ACTION LISTENER......................................................................
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {			//QUANDO CLICA EM START VERIFICA
			if(started == false) {					//SE O BOTÃO TEM ESTADO FALSO
				started = true;						//MUDA PARA TRUE
				startButton.setText("STOP");		//MUDA O NOME DO BOTÃO START PARA STOP
				start();							//CHAMA O METODO START (LIGA O TIMER)
			}else {						
				started = false;					//MUDA PARA TRUE
				startButton.setText("START");		//MUDA O NOME DO BOTÃO STOP PARA START
				stop();								//CHAMA O METODO STOP (PAUSA O TIMER)
			}	
		}
		if(e.getSource() == resetButton) {			//RESETA
			started = false;
			startButton.setText("START");
			reset();								//CHAMA O METODO RESET
		}	
	}

//METODOS..................................................................................
	void start() {
		timer.start();								//LIGA O TIMER
	}
	void stop() {	
		timer.stop();								//PAUSA O TIMER
	}
	void reset() {
		timer.stop();	
		
		elapsedTime	= 	0;
		seconds 	= 	0;
		minutes 	= 	0;
		hours	 	= 	0;
		
		seconds_string 	= String.format("%02d", seconds);	//ATUALIZA
		minutes_string 	= String.format("%02d", minutes);	//ATUALIZA
		hours_string 	= String.format("%02d", hours);		//ATUALIZA	
		
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string); //RÓTULO hh:mm:ss
	}
	
	
}
