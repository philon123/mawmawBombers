Graphics 1024,768,16,1
SetBuffer BackBuffer()
SeedRnd MilliSecs()

;variablen
Global Sound=1 ;Sound im Spiel (-1=aus; 1=ein)
Global chnGameMusic ;Kanal für Spielmusik
Global chnGame ;Kanal Für soundeffekte im Spiel
Global chnMenueMusic ;Kanal für Spielmusik
Global chnMenue ;Kanal Für soundeffekte im Spiel
Global currTime ;wichtig für animationen
Global oldTime  ;wichtig für animationen
Global playerAnzahl=3 ;anzahl der spieler (fängt bei 0 an!)
Global playerZeit=250 ;zeit zwischen schritten der spieler
Global gamePlaying=1 ;ist das spiel in Gange? (0=nein; 1=ja)
Global screenshots=0

;landschaft
Global imgfeld0=LoadImage("bilder/icnWiese.bmp")
Global imgfeld1=LoadImage("bilder/icnFels.bmp")
Global imgfeld2=LoadImage("bilder/icnGeroell.bmp")

;sound
If Sound=1 chnMenueMusic=PlayMusic("sound/dance-zone.mp3")
Global sndMenueSelect   =LoadSound("sound/button-9.wav")
Global sndMenueClick    =LoadSound("sound/button-7.wav")
SoundVolume sndMenueClick,0.5
Global sndItemEinsammeln=LoadSound("sound/button-3.wav")
Global sndGameOver      =LoadSound("sound/scream of anguish.wav")
Global sndGameWin       =LoadSound("sound/CROWD SCREAM YES 01.wav")
Global sndExplosion     =LoadSound("sound/bazooka.wav")
SoundVolume sndExplosion,0.4

;player-animationen
;1
Global    imgPlayer1links =LoadAnimImage("bilder/animMensch1links.bmp", 32,32,0,8)
Global    imgPlayer1rechts=LoadAnimImage("bilder/animMensch1rechts.bmp",32,32,0,8)
Global    imgPlayer1oben  =LoadAnimImage("bilder/animMensch1oben.bmp",  32,32,0,8)
Global    imgPlayer1unten =LoadAnimImage("bilder/animMensch1unten.bmp", 32,32,0,8)
MaskImage imgPlayer1links ,255,255,255
MaskImage imgPlayer1rechts,255,255,255
MaskImage imgPlayer1oben  ,255,255,255
MaskImage imgPlayer1unten ,255,255,255

;2
Global    imgPlayer2links =LoadAnimImage("bilder/animMensch2links.bmp", 32,32,0,8)
Global    imgPlayer2rechts=LoadAnimImage("bilder/animMensch2rechts.bmp",32,32,0,8)
Global    imgPlayer2oben  =LoadAnimImage("bilder/animMensch2oben.bmp",  32,32,0,8)
Global    imgPlayer2unten =LoadAnimImage("bilder/animMensch2unten.bmp", 32,32,0,8)
MaskImage imgPlayer2links ,255,255,255
MaskImage imgPlayer2rechts,255,255,255
MaskImage imgPlayer2oben  ,255,255,255
MaskImage imgPlayer2unten ,255,255,255

;3
Global    imgPlayer3links =LoadAnimImage("bilder/animMensch3links.bmp", 32,32,0,8)
Global    imgPlayer3rechts=LoadAnimImage("bilder/animMensch3rechts.bmp",32,32,0,8)
Global    imgPlayer3oben  =LoadAnimImage("bilder/animMensch3oben.bmp",  32,32,0,8)
Global    imgPlayer3unten =LoadAnimImage("bilder/animMensch3unten.bmp", 32,32,0,8)
MaskImage imgPlayer3links ,255,255,255
MaskImage imgPlayer3rechts,255,255,255
MaskImage imgPlayer3oben  ,255,255,255
MaskImage imgPlayer3unten ,255,255,255

;4
Global    imgPlayer4links =LoadAnimImage("bilder/animMensch4links.bmp", 32,32,0,8)
Global    imgPlayer4rechts=LoadAnimImage("bilder/animMensch4rechts.bmp",32,32,0,8)
Global    imgPlayer4oben  =LoadAnimImage("bilder/animMensch4oben.bmp",  32,32,0,8)
Global    imgPlayer4unten =LoadAnimImage("bilder/animMensch4unten.bmp", 32,32,0,8)
MaskImage imgPlayer4links ,255,255,255
MaskImage imgPlayer4rechts,255,255,255
MaskImage imgPlayer4oben  ,255,255,255
MaskImage imgPlayer4unten ,255,255,255


;Item - bilder
Global    imgItemBomben     =LoadImage("bilder/icnBomben.bmp")
Global    imgItemBlitze     =LoadImage("bilder/icnBlitze.bmp")

;GameWin - bilder
Global    imgGameWin1      =LoadImage("bilder/backGameOver1.jpg")
Global    imgGameWin2      =LoadImage("bilder/backGameOver2.jpg")
Global    imgGameWin3      =LoadImage("bilder/backGameOver3.jpg")
Global    imgGameWin4      =LoadImage("bilder/backGameOver4.jpg")


;bombe+Feuer
Global    imgBombe          =LoadAnimImage("bilder/animbombe.bmp",          32,32,0,9)
MaskImage imgBombe,          255,255,255
Global    imgFeuerMitte     =LoadAnimImage("bilder/animFeuerMitte.bmp",     32,32,0,7)
MaskImage imgFeuerMitte,     255,255,255
Global    imgFeuerWaagrecht =LoadAnimImage("bilder/animFeuerWaagrecht.bmp", 32,32,0,7)
MaskImage imgFeuerWaagrecht, 255,255,255
Global    imgFeuerSenkrecht =LoadAnimImage("bilder/animFeuerSenkrecht.bmp", 32,32,0,7)
MaskImage imgFeuerSenkrecht, 255,255,255
Global    imgFeuerEndeOben  =LoadAnimImage("bilder/animFeuerEndeOben.bmp",  32,32,0,7)
MaskImage imgFeuerEndeOben,  255,255,255
Global    imgFeuerEndeUnten =LoadAnimImage("bilder/animFeuerEndeUnten.bmp", 32,32,0,7)
MaskImage imgFeuerEndeUnten, 255,255,255
Global    imgFeuerEndeLinks =LoadAnimImage("bilder/animFeuerEndeLinks.bmp", 32,32,0,7)
MaskImage imgFeuerEndeLinks, 255,255,255
Global    imgFeuerEndeRechts=LoadAnimImage("bilder/animFeuerEndeRechts.bmp",32,32,0,7)
MaskImage imgFeuerEndeRechts,255,255,255
Global    imgSteinZerstoert =LoadAnimImage("bilder/animGeroell.bmp",32,32,0,7)
MaskImage imgSteinZerstoert, 255,255,255

;Backgrounds
Global imgMenueStartseite =LoadImage("bilder/backHauptmenue.jpg")
Global imgMenuePause      =LoadImage("bilder/backPausenmenue.jpg")
Global backTutorial1      =LoadImage("bilder/backTutorial1.jpg")
Global backTutorial2      =LoadImage("bilder/backTutorial2.jpg") 
Global backTutorial3      =LoadImage("bilder/backTutorial3.jpg")
Global backCredits        =LoadImage("bilder/backCredits.jpg")

;Menue Items
Global    imgMenueStart      =LoadImage("bilder/mnuStart.bmp")    : Global    imgMenueStart1     =LoadImage("bilder/mnuStart1.bmp")
Global    imgMenueOptionen   =LoadImage("bilder/mnuOptionen.bmp") : Global    imgMenueOptionen1  =LoadImage("bilder/mnuOptionen1.bmp")
Global    imgMenueExit       =LoadImage("bilder/mnuExit.bmp")     : Global    imgMenueExit1      =LoadImage("bilder/mnuExit1.bmp")
Global    imgMenuePlayers    =LoadImage("bilder/mnuPlayers.bmp")  : Global    imgMenuePlayers1   =LoadImage("bilder/mnuPlayers1.bmp")
Global    imgMenueBack       =LoadImage("bilder/mnuBack.bmp")     : Global    imgMenueBack1      =LoadImage("bilder/mnuBack1.bmp")
Global    imgMenueBackToMenu =LoadImage("bilder/mnuBackToMenu.bmp"):Global    imgMenueBackToMenu1=LoadImage("bilder/mnuBackToMenu1.bmp")
Global    imgMenueOn         =LoadImage("bilder/mnuOn.bmp")       : Global    imgMenueOn1        =LoadImage("bilder/mnuOn1.bmp")
Global    imgMenueOff        =LoadImage("bilder/mnuOff.bmp")      : Global    imgMenueOff1       =LoadImage("bilder/mnuOff1.bmp")
Global    imgMenueResume     =LoadImage("bilder/mnuResume.bmp")   : Global    imgMenueResume1    =LoadImage("bilder/mnuResume1.bmp")
Global    imgMenueSound      =LoadImage("bilder/mnuSound.bmp")    : Global    imgMenueSound1     =LoadImage("bilder/mnuSound1.bmp")
Global    imgMenueHelp       =LoadImage("bilder/mnuHelp.bmp")     : Global    imgMenueHelp1      =LoadImage("bilder/mnuHelp1.bmp")
Global    imgMenueCredits    =LoadImage("bilder/mnuCredits.bmp")  : Global    imgMenueCredits1   =LoadImage("bilder/mnuCredits1.bmp")
Global    imgMenueZahlen     =LoadAnimImage("bilder/mnuZahlen.bmp",128,128,0,8)

MidHandle imgMenueStart    : MidHandle imgMenueStart1
MidHandle imgMenueOptionen : MidHandle imgMenueOptionen1
MidHandle imgMenueExit     : MidHandle imgMenueExit1
MidHandle imgMenuePlayers  : MidHandle imgMenuePlayers1
MidHandle imgMenueBack     : MidHandle imgMenueBack1
MidHandle imgMenueBackToMenu : MidHandle imgMenueBackToMenu1
MidHandle imgMenueSound    : MidHandle imgMenueSound1
MidHandle imgMenueOn       : MidHandle imgMenueOn1
MidHandle imgMenueOff      : MidHandle imgMenueOff1
MidHandle imgMenueResume   : MidHandle imgMenueResume1
MidHandle imgMenueHelp     : MidHandle imgMenueHelp1
MidHandle imgMenueCredits  : MidHandle imgMenueCredits1
MidHandle imgMenueZahlen

MaskImage imgMenueStart,    255,255,255 : MaskImage imgMenueStart1,   255,255,255
MaskImage imgMenueOptionen, 255,255,255 : MaskImage imgMenueOptionen1,255,255,255
MaskImage imgMenueExit,     255,255,255 : MaskImage imgMenueExit1,    255,255,255
MaskImage imgMenuePlayers,  255,255,255 : MaskImage imgMenuePlayers1, 255,255,255
MaskImage imgMenueBack,     255,255,255 : MaskImage imgMenueBack1,    255,255,255
MaskImage imgMenueBackToMenu,255,255,255: MaskImage imgMenueBackToMenu1,255,255,255
MaskImage imgMenueSound,    255,255,255 : MaskImage imgMenueSound1,   255,255,255
MaskImage imgMenueOn,       255,255,255 : MaskImage imgMenueOn1,      255,255,255
MaskImage imgMenueOff,      255,255,255 : MaskImage imgMenueOff1,     255,255,255
MaskImage imgMenueResume,   255,255,255 : MaskImage imgMenueResume1,  255,255,255
MaskImage imgMenueHelp,     255,255,255 : MaskImage imgMenueHelp1,    255,255,255
MaskImage imgMenueCredits,  255,255,255 : MaskImage imgMenueCredits1, 255,255,255
MaskImage imgMenueZahlen,   255,255,255

;player-variablen
Dim player(playerAnzahl,13)
	;  0 = xPos
	;  1 = yPos
	;  2 = Blickrichtung (0=oben, 1=unten, 2=links, 3=rechts)
	;  3 = Aktive Bomben
	;  4 = Bombengroesse
	;  5 = Tot (0=nein, 1=ja)
	;  6 = Taste für oben
	;  7 = Taste für unten
	;  8 = Taste für links
	;  9 = Taste für rechts
	; 10 = Taste für Bombe legen
	; 11 = zeit der letzten Aktion (millisecs)
	; 12 = Max. Bomben
	; 13 = Aktueller Animationsframe

;spielfeld
Dim spielfeld(30,22,3)
;0. Ebene: Landverteilung
	;-1=startfeld, 0=Wiese, 1=Stein
	
;1. Ebene: Bomben
	;0=keine Bombe, 1=Bombe
	
;2. Ebene: Items
	;0=kein Item, 1=Blitz, 2=mehr Bomben
	
;3. Ebene: Explosionen
	;0=keine Explosion, 1=Explosion

Data 1, 1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1, 1,1
Data 1,-1,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,-1,1
Data 1,-1, 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0, 1,-1,1
Data 1, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0,1
Data 1, 0, 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0, 1, 0,1
Data 1, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0,1
Data 1, 0, 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0, 1, 0,1
Data 1, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0,1
Data 1, 0, 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0, 1, 0,1
Data 1, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0,1
Data 1, 0, 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0, 1, 0,1
Data 1, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0,1
Data 1, 0, 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0, 1, 0,1
Data 1, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0,1
Data 1, 0, 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0, 1, 0,1
Data 1, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0,1
Data 1, 0, 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0, 1, 0,1
Data 1, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0,1
Data 1, 0, 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0, 1, 0,1
Data 1, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0,1
Data 1,-1, 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0, 1,-1,1
Data 1,-1,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,-1,1
Data 1, 1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1, 1,1
For y=0 To 22
	For x=0 To 30
		Read spielfeld(x,y,0)
		If spielfeld(x,y,0)=0 Then
			wert=Rand(0,1)
			If wert=0 spielfeld(x,y,0)=0
			If wert=1 spielfeld(x,y,0)=2
		EndIf
	Next
Next

;types

;bombe
Type bombe
	Field xPos
	Field yPos
	Field startZeit
	Field pausenZeit
	Field Groesse
	Field animFrame
	Field player
End Type

;explosionen
Type explosion
	Field xPos
	Field yPos
	Field startZeit
	Field pausenZeit
	Field Groesse
	Field oben
	Field links
	Field unten
	Field rechts
End Type

;GeroellAnimationen
Type zerbroeckeln
	Field xPos
	Field yPos
	Field frame
	Field startZeit
End Type

playerAnzahl=1

;########################## HAUPTMENUE
.hauptMenue
gamePlaying=1
yPosition=1
xPosition=2
restoreGame()
FlushKeys()
If ChannelPlaying(chnMenueMusic)=0 And sound=1 chnMenueMusic=PlayMusic("sound/dance-zone.mp3")
Repeat
	
	DrawBlock imgMenueStartseite,0,0
	
	
	If yPosition=1 And xPosition=2 Then
		DrawImage imgMenueStart1,   512,200
	Else DrawImage imgMenueStart,  512,200
	EndIf
	
	If yPosition=2 And xPosition=2 Then
		DrawImage imgMenueOptionen1,512,400
	Else DrawImage imgMenueOptionen,512,400
	EndIf
	
	If yPosition=3 And xPosition=2 Then
		DrawImage imgMenueExit1,    512,600
	Else DrawImage imgMenueExit,   512,600
	EndIf
	
	If xPosition=1 Then
		DrawImage imgMenueHelp1,    200,768/2
	Else DrawImage imgMenueHelp,   200,768/2
	EndIf
	
	If xPosition=3 Then
		DrawImage imgMenueCredits1,   850,400
	Else DrawImage imgMenueCredits,   850,400
	EndIf
	
	If KeyHit(200) Then
		yPosition=yPosition-1
		If Sound=1 chnMenue=PlaySound (sndMenueSelect)
	EndIf
	
	If KeyHit(208) Then
		yPosition=yPosition+1
		If Sound=1 chnMenue=PlaySound (sndMenueSelect)
	EndIf
	
	If KeyHit(203) Then
		xPosition=xPosition-1
		If Sound=1 chnMenue=PlaySound (sndMenueSelect)
	EndIf
	
	If KeyHit(205) Then
		xPosition=xPosition+1
		If Sound=1 chnMenue=PlaySound (sndMenueSelect)
	EndIf
	
	If yPosition<1 yPosition=3
	If yPosition>3 yPosition=1
	If xPosition<1 xPosition=3
	If xPosition>3 xPosition=1
	
	If KeyHit(28) Then
		If Sound=1 chnMenue=PlaySound (sndMenueClick)
		If yPosition=1 And xPosition=2 Then
			StopChannel chnMenueMusic
			If Sound=1 chnGameMusic=PlayMusic ("sound/midnight-ride.mp3")
			Goto startGame
		EndIf
		If yPosition=2 And xPosition=2 Goto optionenMenue
		If yPosition=3 And xPosition=2 End
		If xPosition=1 Goto Tutorial
		If xPosition=3 Goto Credits
		FlushKeys()
	EndIf
	
	
	Flip
	Cls
Forever
;########################## OPTIONENMENUE

.optionenMenue
yPosition=1
Repeat
	
	DrawBlock imgMenueStartseite,0,0
	
	
	If yPosition=1 Then
		DrawImage imgMenuePlayers1,  512,200
	Else DrawImage imgMenuePlayers, 512,200
	EndIf
	
	If yPosition=2 Then
		DrawImage imgMenueSound1,    512,400
	Else DrawImage imgMenueSound,   512,400
	EndIf
	
	If yPosition=3 Then
		DrawImage imgMenueBack1,     512,600
	Else DrawImage imgMenueBack,    512,600
	EndIf
	
	If yPosition=1 Then
		DrawImage imgMenueZahlen,    800,200,playerAnzahl*2+1
	Else DrawImage imgMenueZahlen,  800,200,playerAnzahl*2
	EndIf
	
	If yPosition=2 Then
		If Sound=-1 DrawImage imgMenueOff1,800,400
		If Sound=1  DrawImage imgMenueOn1, 800,400
	Else
		If Sound=-1 DrawImage imgMenueOff,800,400
		If Sound=1  DrawImage imgMenueOn, 800,400
	EndIf
		
	If KeyHit(200) Then	
		yPosition=yPosition-1
		If Sound=1 chnMenue=PlaySound (sndMenueSelect)
	EndIf
	
	If KeyHit(208) Then
		yPosition=yPosition+1
		If Sound=1 chnMenue=PlaySound (sndMenueSelect)
	EndIf
	
	
	If KeyHit(203) Then
		If yPosition=1 Then
			playerAnzahl=playerAnzahl-1
			If playerAnzahl<1 playerAnzahl=3
			If Sound=1 chnMenue=PlaySound (sndMenueClick)
		EndIf
		
		If yPosition=2 Then
			Sound=Sound*-1
			If Sound=-1 PauseChannel chnMenueMusic
			If Sound=1 ResumeChannel chnMenueMusic
		EndIf
	EndIf
	
	If KeyHit(205) Then
		If yPosition=1 Then
			playerAnzahl=playerAnzahl+1
			If playerAnzahl>3 playerAnzahl=1
			If Sound=1 chnMenue=PlaySound (sndMenueClick)
		EndIf
		
		If yPosition=2 Then
			Sound=Sound*-1
			If Sound=-1 PauseChannel chnMenueMusic
			If Sound=1 ResumeChannel chnMenueMusic
		EndIf
	EndIf
	
	
	If yPosition<1 yPosition=3
	If yPosition>3 yPosition=1
	
	If KeyHit(28) Then
		If Sound=1 chnMenue=PlaySound (sndMenueClick)
		If yPosition=3 Goto hauptMenue
	EndIf
	
	
	Flip
	Cls
	
Forever
;########################## PAUSENMENUE
.pausenMenue
FlushKeys()
yPosition=1
Cls
Repeat
	
	DrawBlock imgMenuePause,0,0
	
	If yPosition=1 Then
		DrawImage imgMenueResume1,     1024/2,200
	Else DrawImage imgMenueResume,    1024/2,200
	EndIf
	
	If yPosition=2 Then
		DrawImage imgMenueSound1,      1024/2,350
	Else DrawImage imgMenueSound,     1024/2,350
	EndIf
	
	If yPosition=3 Then
		DrawImage imgMenueBackToMenu1, 1024/2,500
	Else DrawImage imgMenueBackToMenu,1024/2,500
	EndIf
	
	If yPosition=4 Then
		DrawImage imgMenueExit1,       1024/2,650
	Else DrawImage imgMenueExit,      1024/2,650
	EndIf
	
	If yPosition=2 Then
		If Sound=-1 DrawImage imgMenueOff1,800,350
		If Sound=1  DrawImage imgMenueOn1, 800,350
	Else
		If Sound=-1 DrawImage imgMenueOff,800,350
		If Sound=1  DrawImage imgMenueOn, 800,350
	EndIf
	
	
	If KeyHit(200) Then	
		yPosition=yPosition-1
		If Sound=1 chnMenue=PlaySound (sndMenueSelect)
	EndIf
	
	If KeyHit(208) Then
		yPosition=yPosition+1
		If Sound=1 chnMenue=PlaySound (sndMenueSelect)
	EndIf
	
	If yPosition<1 yPosition=4
	If yPosition>4 yPosition=1
	
	If KeyHit(203) Then
		
		If yPosition=2 Then
			Sound=Sound*-1
			If Sound=-1 PauseChannel chnGameMusic
			If Sound=1 ResumeChannel chnGameMusic
				
		EndIf
	EndIf
	
	If KeyHit(205) Then
		
		If yPosition=2 Then
			Sound=Sound*-1
			If Sound=-1 PauseChannel chnGameMusic
			If Sound=1 ResumeChannel chnGameMusic
		EndIf
	EndIf
	
	If Sound=1 Then
		If ChannelPlaying(chnGameMusic)=0
			chnGameMusic=PlayMusic ("sound/midnight-ride.mp3")
		EndIf
	EndIf
	
	If KeyHit(1)=1 Return
	
	If KeyHit(28) Then
		If Sound=1 chnMenue=PlaySound (sndMenueClick)
		If yPosition=1 Return
		If yPosition=3 Then
			StopChannel chnGameMusic
			For bombe.bombe=Each bombe
				Delete bombe
			Next
			For explosion.explosion=Each explosion
				Delete explosion
			Next
			For x=0 To 30
				For y=0 To 22
					For z=1 To 3
						spielfeld(x,y,1)=0
						spielfeld(x,y,2)=0
						spielfeld(x,y,3)=0
					Next
				Next
			Next
			
			Goto hauptMenue
		EndIf
		
		If yPosition=4 End
	EndIf
	
	Flip
	Cls
	
Forever

;########################## TUTORIAL
.Tutorial
page=1
Repeat 
	
	Cls
	
	If page=1 DrawBlock backTutorial1,0,0
	If page=2 DrawBlock backTutorial2,0,0
	If page=3 DrawBlock backTutorial3,0,0
	If page>3 Goto hauptMenue
	
	If KeyHit(28)=1 page=page+1
	
	Flip
	
Forever

;########################## CREDITS
.credits
Repeat 
	
	Cls
	DrawBlock backCredits,0,0
	
	If KeyHit(28)=1 Goto hauptmenue
	
	Flip
	
Forever

;########################## SPIELSCHLEIFE
.startGame
zeichnen()
Delay 1000
FlushKeys()

Repeat
	
	zeichnen()
	keyhits()
	
	If gamePlaying=0 Goto hauptMenue

	;pause
	currtime=MilliSecs()
	If KeyHit(1)=1 Then
		
		For bombe.bombe=Each bombe
			bombe\pausenZeit=currtime
		Next
		For explosion.explosion=Each explosion
			explosion\pausenZeit=currtime
		Next
		
		Gosub pausenMenue
		
		currtime=MilliSecs()
		
		For bombe.bombe=Each bombe
			bombe\startZeit=bombe\startZeit+(currtime-bombe\pausenZeit)
		Next
		For explosion.explosion=Each explosion
			explosion\startZeit=explosion\startZeit+(currtime-explosion\pausenZeit)
		Next
	EndIf
	
	
	
	Flip
	If KeyHit(68)=1 screenshot()
	Cls

Forever
End
;##########################
Function zeichnen()
	
	;felder
	For y=0 To 22
		For x=0 To 30
		
			If spielfeld(x,y,0)= 0 DrawImage imgfeld0,x*32,y*32
			If spielfeld(x,y,0)= 1 DrawImage imgfeld1,x*32,y*32
			If spielfeld(x,y,0)= 2 DrawImage imgfeld2,x*32,y*32
			If spielfeld(x,y,0)=-1 DrawImage imgfeld0,x*32,y*32

		Next
	Next
	
	;zerstoerte Steine
	bombenZeit=200
	currtime=MilliSecs()
	For zerbroeckeln.zerbroeckeln=Each zerbroeckeln
		If currtime<zerbroeckeln\startZeit+bombenZeit Then
			vergangeneZeit=currtime-zerbroeckeln\startZeit
			
			If vergangeneZeit<bombenZeit/7*1 And vergangeneZeit>bombenZeit/7*0 frame=0
			If vergangeneZeit<bombenZeit/7*2 And vergangeneZeit>bombenZeit/7*1 frame=1
			If vergangeneZeit<bombenZeit/7*3 And vergangeneZeit>bombenZeit/7*2 frame=2
			If vergangeneZeit<bombenZeit/7*4 And vergangeneZeit>bombenZeit/7*3 frame=3
			If vergangeneZeit<bombenZeit/7*5 And vergangeneZeit>bombenZeit/7*4 frame=4
			If vergangeneZeit<bombenZeit/7*6 And vergangeneZeit>bombenZeit/7*5 frame=5
			If vergangeneZeit<bombenZeit/7*7 And vergangeneZeit>bombenZeit/7*6 frame=6
			
			DrawImage imgSteinZerstoert,zerbroeckeln\xPos*32,zerbroeckeln\yPos*32,frame
		
		Else Delete zerbroeckeln
		EndIf
	Next
	
	
	
	;explosionen
	zeichneExplosionen()
	
	;bomben
	For bombe.bombe=Each bombe
		currtime=MilliSecs()
		
		If  currtime<(bombe\startZeit+1500) Then
			DrawImage imgBombe,bombe\xPos*32,bombe\yPos*32,0
		ElseIf currtime<(bombe\startZeit+3000) And (currtime>bombe\startZeit+1500) Then
			
			DrawImage imgBombe,bombe\xPos*32,bombe\yPos*32,bombe\animFrame
			
			bombe\animFrame=bombe\animFrame+1
			If bombe\animFrame>8 bombe\animframe=0
		EndIf
		
		If currtime>(bombe\startZeit+3000) Then
			bombenExplosion(bombe\xPos,bombe\yPos,player(bombe\player,4))
			player(bombe\player,12)=player(bombe\player,12)-1
			spielfeld(bombe\xPos,bombe\yPos,1)=0
			Delete bombe
			Exit
		EndIf
		
		If spielfeld(bombe\xPos,bombe\yPos,3)=1 Then
			bombenExplosion(bombe\xPos,bombe\yPos,player(bombe\player,4))
			player(bombe\player,12)=player(bombe\player,12)-1
			spielfeld(bombe\xPos,bombe\yPos,1)=0
			Delete bombe
			Exit
		EndIf
		
	Next
	
	;player
	currtime=MilliSecs()
	
	For p=0 To playerAnzahl
		If player(p,5)=0 Then
			
			currtime=MilliSecs()
			
			pvergangeneZeit=currtime-player(p,11)
			If pvergangeneZeit<playerZeit/8*1 And pvergangeneZeit>playerZeit/8*0 player(p,13)=0
			If pvergangeneZeit<playerZeit/8*2 And pvergangeneZeit>playerZeit/8*1 player(p,13)=1
			If pvergangeneZeit<playerZeit/8*3 And pvergangeneZeit>playerZeit/8*2 player(p,13)=2
			If pvergangeneZeit<playerZeit/8*4 And pvergangeneZeit>playerZeit/8*3 player(p,13)=3
			If pvergangeneZeit<playerZeit/8*5 And pvergangeneZeit>playerZeit/8*4 player(p,13)=4
			If pvergangeneZeit<playerZeit/8*6 And pvergangeneZeit>playerZeit/8*5 player(p,13)=5
			If pvergangeneZeit<playerZeit/8*7 And pvergangeneZeit>playerZeit/8*6 player(p,13)=6
			If pvergangeneZeit<playerZeit/8*8 And pvergangeneZeit>playerZeit/8*7 player(p,13)=7
			If pvergangeneZeit>playerZeit/8*8 player(p,13)=-1
		
			;blitze einsammeln
			If spielfeld(player(p,0),player(p,1),2)=1 Then
				spielfeld(player(p,0),player(p,1),2)=0
				player(p,4)=player(p,4)+1
				If Sound=1 chnGame=PlaySound(sndItemEinsammeln)
			EndIf
			
			;bomben einsammeln
			If spielfeld(player(p,0),player(p,1),2)=2 Then
				spielfeld(player(p,0),player(p,1),2)=0
				player(p,3)=player(p,3)+1
				If Sound=1 chnGame=PlaySound(sndItemEinsammeln)
			EndIf
			
			
			
			If p=0 Then
				If player(0,13)>-1 Then
					If player(0,2)=1 DrawImage imgPlayer1oben,  player(0,0)*32                      ,player(0,1)*32-(player(0,13)+1)*4+32,player(0,13)
					If player(0,2)=2 DrawImage imgPlayer1unten, player(0,0)*32                      ,player(0,1)*32+(player(0,13)+1)*4-32,player(0,13)
					If player(0,2)=3 DrawImage imgPlayer1links, player(0,0)*32-(player(0,13)+1)*4+32,player(0,1)*32, player(0,13)
					If player(0,2)=4 DrawImage imgPlayer1rechts,player(0,0)*32+(player(0,13)+1)*4-32,player(0,1)*32, player(0,13)
				Else 
					If player(0,2)=1 DrawImage imgPlayer1oben,  player(0,0)*32, player(0,1)*32,0
					If player(0,2)=2 DrawImage imgPlayer1unten, player(0,0)*32, player(0,1)*32,0
					If player(0,2)=3 DrawImage imgPlayer1links, player(0,0)*32, player(0,1)*32,0
					If player(0,2)=4 DrawImage imgPlayer1rechts,player(0,0)*32, player(0,1)*32,0
				EndIf
			EndIf
			
			If p=1 Then
				If player(1,13)>-1 Then
					If player(1,2)=1 DrawImage imgPlayer2oben,  player(1,0)*32                      ,player(1,1)*32-(player(1,13)+1)*4+32,player(1,13)
					If player(1,2)=2 DrawImage imgPlayer2unten, player(1,0)*32                      ,player(1,1)*32+(player(1,13)+1)*4-32,player(1,13)
					If player(1,2)=3 DrawImage imgPlayer2links, player(1,0)*32-(player(1,13)+1)*4+32,player(1,1)*32, player(1,13)
					If player(1,2)=4 DrawImage imgPlayer2rechts,player(1,0)*32+(player(1,13)+1)*4-32,player(1,1)*32, player(1,13)
				Else 
					If player(1,2)=1 DrawImage imgPlayer2oben,  player(1,0)*32, player(1,1)*32,0
					If player(1,2)=2 DrawImage imgPlayer2unten, player(1,0)*32, player(1,1)*32,0
					If player(1,2)=3 DrawImage imgPlayer2links, player(1,0)*32, player(1,1)*32,0
					If player(1,2)=4 DrawImage imgPlayer2rechts,player(1,0)*32, player(1,1)*32,0
				EndIf
			EndIf
			
			If p=2 Then
				If player(2,13)>-1 Then
					If player(2,2)=1 DrawImage imgPlayer3oben,  player(2,0)*32                      ,player(2,1)*32-(player(2,13)+1)*4+32,player(2,13)
					If player(2,2)=2 DrawImage imgPlayer3unten, player(2,0)*32                      ,player(2,1)*32+(player(2,13)+1)*4-32,player(2,13)
					If player(2,2)=3 DrawImage imgPlayer3links, player(2,0)*32-(player(2,13)+1)*4+32,player(2,1)*32, player(2,13)
					If player(2,2)=4 DrawImage imgPlayer3rechts,player(2,0)*32+(player(2,13)+1)*4-32,player(2,1)*32, player(2,13)
				Else 
					If player(2,2)=1 DrawImage imgPlayer3oben,  player(2,0)*32, player(2,1)*32,0
					If player(2,2)=2 DrawImage imgPlayer3unten, player(2,0)*32, player(2,1)*32,0
					If player(2,2)=3 DrawImage imgPlayer3links, player(2,0)*32, player(2,1)*32,0
					If player(2,2)=4 DrawImage imgPlayer3rechts,player(2,0)*32, player(2,1)*32,0
				EndIf
			EndIf
			
			If p=3 Then
				If player(3,13)>-1 Then
					If player(3,2)=1 DrawImage imgPlayer4oben,  player(3,0)*32                      ,player(3,1)*32-(player(3,13)+1)*4+32,player(3,13)
					If player(3,2)=2 DrawImage imgPlayer4unten, player(3,0)*32                      ,player(3,1)*32+(player(3,13)+1)*4-32,player(3,13)
					If player(3,2)=3 DrawImage imgPlayer4links, player(3,0)*32-(player(3,13)+1)*4+32,player(3,1)*32, player(3,13)
					If player(3,2)=4 DrawImage imgPlayer4rechts,player(3,0)*32+(player(3,13)+1)*4-32,player(3,1)*32, player(3,13)
				Else 
					If player(3,2)=1 DrawImage imgPlayer4oben,  player(3,0)*32, player(3,1)*32,0
					If player(3,2)=2 DrawImage imgPlayer4unten, player(3,0)*32, player(3,1)*32,0
					If player(3,2)=3 DrawImage imgPlayer4links, player(3,0)*32, player(3,1)*32,0
					If player(3,2)=4 DrawImage imgPlayer4rechts,player(3,0)*32, player(3,1)*32,0
				EndIf
			EndIf
			
			;wird player gertroffen?
			If spielfeld(player(p,0),player(p,1),3)=1 Then
				zeichneExplosionen()
				Flip
				gameOver(p)
			EndIf
			
		EndIf
	Next
	
	
	
	;Items
	For x=0 To 30
		For y=0 To 22
			If spielfeld(x,y,2)=1 DrawBlock imgItemBlitze,x*32,y*32
			If spielfeld(x,y,2)=2 DrawBlock imgItemBomben,x*32,y*32
		Next
	Next
	
	
End Function

Function keyhits()
		
		
		currTime=MilliSecs()
		
		If KeyHit(68) Then
  			SaveBuffer FrontBuffer(), "screenshot.bmp"
		End If
		
		For p=0 To playerAnzahl
			If player(p,5)=0 Then
			
				If currTime>player(p,11)+250 Then
				
					If KeyDown(player(p,6)) test=1
					If KeyDown(player(p,7)) test=test+1
					If KeyDown(player(p,8)) test=test+1
					If KeyDown(player(p,9)) test=test+1
					If test>1 Exit
					
					;Bewegung
					
					;oben
					If KeyDown(player(p,6)) Then
						player(p,2)=1
						If spielfeld(player(p,0),player(p,1)-1,0)=<0 And spielfeld(player(p,0),player(p,1)-1,1)=0 Then
							player(p,1)=player(p,1)-1
							player(p,11)=MilliSecs()
						EndIf
					EndIf
					
					;unten
					If KeyDown(player(p,7)) Then
						player(p,2)=2
						If spielfeld(player(p,0),player(p,1)+1,0)=<0 And spielfeld(player(p,0),player(p,1)+1,1)=0 Then
							player(p,1)=player(p,1)+1
							player(p,11)=MilliSecs()
						EndIf
					EndIf
					
					;links
					If KeyDown(player(p,8)) Then
						player(p,2)=3
						If spielfeld(player(p,0)-1,player(p,1),0)=<0 And spielfeld(player(p,0)-1,player(p,1),1)=0 Then
							player(p,0)=player(p,0)-1
							player(p,11)=MilliSecs()
						EndIf
					EndIf
					
					;rechts
					If KeyDown(player(p,9)) Then
						player(p,2)=4
						If spielfeld(player(p,0)+1,player(p,1),0)=<0 And spielfeld(player(p,0)+1,player(p,1),1)=0 Then
							player(p,0)=player(p,0)+1
							player(p,11)=MilliSecs()
						EndIf
					EndIf
					
				EndIf
			EndIf
		Next
			
		For p=0 To playerAnzahl
			If player(p,5)=0 Then
				;Bomben legen
				If KeyDown(player(p,10)) Then						
					If spielfeld(player(p,0),player(p,1),1)=0 Then
						If player(p,12)<player(p,3) Then 
							spielfeld(player(p,0),player(p,1),1)=1
							player(p,12)=player(p,12)+1
							bombe.bombe= New bombe
							bombe\xPos=player(p,0)
							bombe\yPos=player(p,1)
							bombe\startZeit=MilliSecs()
							bombe\player=p
							bombe\groesse=player(p,4)
						EndIf
					EndIf
				EndIf	
			EndIf		
		Next
		
End Function 


Function bombenExplosion(xPos,yPos,Groesse)
	
	If Sound=1 chnGame=PlaySound(sndExplosion)
	explosion.explosion= New explosion
	explosion\xPos=xPos
	explosion\yPos=yPos
	explosion\startZeit=MilliSecs()
	explosion\groesse=Groesse
	explosion\oben=0
	explosion\links=0
	explosion\unten=0
	explosion\rechts=0
	
End Function


Function steinZerstoert(xPos,yPos)
	
	zerbroeckeln.zerbroeckeln=New zerbroeckeln
	zerbroeckeln\xPos =xPos
	zerbroeckeln\yPos =yPos
	zerbroeckeln\frame=0
	zerbroeckeln\startZeit=MilliSecs()
	
	spielfeld(xPos,yPos,0)=0
	
	zufallsItem=Rand(0,8)
	;blitze
	If zufallsItem=1 Then
		spielfeld(xPos,yPos,2)=1
	EndIf
	
	;bomben
	If zufallsItem=2 Then
		spielfeld(xPos,yPos,2)=2
	EndIf
	
	
End Function


Function GameOver(p)
	If Sound=1 chnGame=PlaySound(sndGameOver)
	player(p,5)=1
	
	uebrig=0
	For p=0 To playerAnzahl
		If player(p,5)=0 uebrig=uebrig+1
	Next
	If uebrig=1 Then
		StopChannel chnGameMusic
		While ChannelPlaying(chnGame)=1
		Wend
		gameWin()
	EndIf
End Function

Function gameWin()
	
	If Sound=1 chnGame=PlaySound(sndGameWin)
	
	For p=0 To playerAnzahl
		If player(p,5)=0 Then
			gewinner=p
			Exit
		EndIf
	Next
	
	If gewinner=0 DrawBlock imgGameWin1,0,0
	If gewinner=1 DrawBlock imgGameWin2,0,0
	If gewinner=2 DrawBlock imgGameWin3,0,0
	If gewinner=3 DrawBlock imgGameWin4,0,0
	
	Flip
	FlushKeys()
	WaitKey()
		
	gamePlaying=0
End Function

Function restoreGame()
	
	player(0, 0)= 1 ;xPos
	player(0, 1)= 1 ;yPos
	player(0, 2)= 2 ;Richtung
	player(0, 3)= 1 ;BomAnzahl
	player(0, 4)= 1 ;BGromoesse
	player(0, 5)= 0 ;playerGameOver
	player(0, 6)=  17 ;Hoch
	player(0, 7)=  31 ;Runter
	player(0, 8)=  30 ;Links
	player(0, 9)=  32 ;Rechts
	player(0,10)=  15 ;BombeLegen
	player(0,11)=0 ;Oldtime
	player(0,12)=0 ;gelegteBomben
	player(0,13)=-1 ;player frame

	player(1, 0)=29 ;xPos
	player(1, 1)=21 ;yPos
	player(1, 2)= 2 ;Richtung
	player(1, 3)= 1 ;BomAnzahl
	player(1, 4)= 1 ;BomGroesse
	player(1, 5)= 0 ;playerGameOver
	player(1, 6)=  200 ;Hoch
	player(1, 7)=  208 ;Runter
	player(1, 8)=  203 ;Links
	player(1, 9)=  205 ;Rechts
	player(1,10)=  157 ;BombeLegen
	player(1,11)=0 ;Oldtime
	player(1,12)=0 ;gelegteBomben
	player(1,13)=-1 ;player frame
	
	player(2, 0)=29 ;xPos
	player(2, 1)= 1 ;yPos
	player(2, 2)= 2 ;Richtung
	player(2, 3)= 1 ;BomAnzahl
	player(2, 4)= 1 ;BomGroesse
	player(2, 5)= 0 ;playerGameOver
	player(2, 6)=  72 ;Hoch
	player(2, 7)=  76 ;Runter
	player(2, 8)=  75 ;Links
	player(2, 9)=  77 ;Rechts
	player(2,10)=  82 ;BombeLegen
	player(2,11)=0 ;Oldtime
	player(2,12)=0 ;gelegteBomben
	player(2,13)=-1 ;player frame
		
	player(3, 0)= 1 ;xPos
	player(3, 1)=21 ;yPos
	player(3, 2)= 2 ;Richtung
	player(3, 3)= 1 ;BomAnzahl
	player(3, 4)= 1 ;BomGroesse
	player(3, 5)= 0 ;playerGameOver
	player(3, 6)=  23 ;Hoch
	player(3, 7)=  37 ;Runter
	player(3, 8)=  36 ;Links
	player(3, 9)=  38 ;Rechts
	player(3,10)=  48 ;BombeLegen
	player(3,11)=0 ;Oldtime
	player(3,12)=0 ;gelegteBomben
	player(3,13)=-1 ;player frame
	
	For x=0 To 30
		For y=0 To 22
			For z=1 To 3
				spielfeld(x,y,z)=0
			Next
		Next
	Next
	
	For bombe.bombe = Each bombe
		Delete bombe
	Next
	For zerbroeckeln.zerbroeckeln = Each zerbroeckeln
		Delete zerbroeckeln
	Next
	For explosion.explosion = Each explosion
		Delete explosion
	Next
	
	For y=0 To 22
		For x=0 To 30
			If spielfeld(x,y,0)=0 Then
				wert=Rand(0,4)
				If wert=0 spielfeld(x,y,0)=0
				If wert=1 Or wert=2 Or wert=3 spielfeld(x,y,0)=2
			EndIf
		Next
	Next
	
End Function 

Function screenshot()
	SetBuffer FrontBuffer()
	SaveBuffer (FrontBuffer,"screenshot.bmp")
	SetBuffer BackBuffer()
End Function

Function zeichneExplosionen()
		For x=0 To 30
		For y=0 To 22
			spielfeld(x,y,3)=0
		Next
	Next
	
	currtime=MilliSecs()
	For explosion.explosion=Each explosion
		
		
		bombenZeit=200
		If currtime<explosion\startZeit+bombenZeit Then
		
			vergangeneZeit=currtime-explosion\startZeit
			
			If vergangeneZeit<bombenZeit/7*1 And vergangeneZeit>bombenZeit/7*0 frame=0
			If vergangeneZeit<bombenZeit/7*2 And vergangeneZeit>bombenZeit/7*1 frame=1
			If vergangeneZeit<bombenZeit/7*3 And vergangeneZeit>bombenZeit/7*2 frame=2
			If vergangeneZeit<bombenZeit/7*4 And vergangeneZeit>bombenZeit/7*3 frame=3
			If vergangeneZeit<bombenZeit/7*5 And vergangeneZeit>bombenZeit/7*4 frame=4
			If vergangeneZeit<bombenZeit/7*6 And vergangeneZeit>bombenZeit/7*5 frame=5
			If vergangeneZeit<bombenZeit/7*7 And vergangeneZeit>bombenZeit/7*6 frame=6
		
			DrawImage imgFeuerMitte,explosion\xPos*32,explosion\yPos*32,frame
			spielfeld(explosion\xPos,explosion\yPos,3)=1
			
			
			;rechts
			For i=1 To explosion\groesse
				
				If spielfeld(explosion\xPos+i,explosion\yPos,3)=1 explosion\rechts=i
				If spielfeld(explosion\xPos+i,explosion\yPos,1)=1 Then
					explosion\rechts=i
					spielfeld(explosion\xPos+i,explosion\yPos,3)=1
				EndIf
				If i=explosion\rechts Exit
				
				;explosion kann sich ausbreiten
				If spielfeld(explosion\xPos+i,explosion\yPos,0)<1 Then
						
					spielfeld(explosion\xPos+i,explosion\yPos,3)=1
					If i<explosion\groesse Then
						DrawImage imgFeuerWaagrecht,(explosion\xPos+i)*32,explosion\yPos*32,frame 
					Else
						DrawImage imgFeuerEndeRechts,(explosion\xPos+i)*32,explosion\yPos*32,frame
					EndIf  
					
				EndIf 

				;explosion gegen block
				If spielfeld(explosion\xPos+i,explosion\yPos,0)=1 Then
					explosion\rechts=i
					Exit
				EndIf
			
				;explosion gegen zerstörbar
				If spielfeld(explosion\xPos+i,explosion\yPos,0)=2 Then
					explosion\rechts=i
					steinZerstoert(explosion\xPos+i,explosion\yPos)
					spielfeld(explosion\xPos+i,explosion\yPos,3)=1
					Exit
				EndIf
			Next

			
			;links
			For i=-1 To -1*explosion\groesse Step -1
				If spielfeld(explosion\xPos+i,explosion\yPos,3)=1 explosion\links=i
				If spielfeld(explosion\xPos+i,explosion\yPos,1)=1 Then
					explosion\links=i
					spielfeld(explosion\xPos+i,explosion\yPos,3)=1
				EndIf
				If i=explosion\links Exit
				
				;explosion kann sich ausbreiten
				If spielfeld(explosion\xPos+i,explosion\yPos,0)<1 Then
					
					spielfeld(explosion\xPos+i,explosion\yPos,3)=1
					If i>explosion\groesse*-1 Then
						DrawImage imgFeuerWaagrecht,(explosion\xPos+i)*32,explosion\yPos*32,frame 
					Else
						DrawImage imgFeuerEndeLinks,(explosion\xPos+i)*32,explosion\yPos*32,frame
					EndIf  
						
				EndIf 

				;explosion gegen block
				If spielfeld(explosion\xPos+i,explosion\yPos,0)=1 Then
					explosion\links=i
					Exit
				EndIf
			
				;explosion gegen zerstörbar
				If spielfeld(explosion\xPos+i,explosion\yPos,0)=2 Then
					explosion\links=i
					steinZerstoert(explosion\xPos+i,explosion\yPos)
					spielfeld(explosion\xPos+i,explosion\yPos,3)=1
					Exit
				EndIf
			Next
			
			;oben
			For i=-1 To -1*explosion\groesse Step -1
				If spielfeld(explosion\xPos,explosion\yPos+i,3)=1 explosion\oben=i
				If spielfeld(explosion\xPos,explosion\yPos+i,1)=1 Then
					explosion\oben=i
					spielfeld(explosion\xPos,explosion\yPos+i,3)=1
				EndIf
				If i=explosion\oben Exit
				
				;explosion kann sich ausbreiten
				If spielfeld(explosion\xPos,explosion\yPos+i,0)<1 Then
										
					spielfeld(explosion\xPos,explosion\yPos+i,3)=1
					If i>explosion\groesse*-1 Then
						DrawImage imgFeuerSenkrecht,explosion\xPos*32,(explosion\yPos+i)*32,frame 
					Else 
						DrawImage imgFeuerEndeOben,explosion\xPos*32,(explosion\yPos+i)*32,frame
					EndIf 
						
				EndIf 
				
				;explosion gegen block
				If spielfeld(explosion\xPos,explosion\yPos+i,0)=1 Then
					explosion\oben=i
					Exit
				EndIf
				
				;explosion gegen zerstörbar
				If spielfeld(explosion\xPos,explosion\yPos+i,0)=2 Then
					explosion\oben=i
					steinZerstoert(explosion\xPos,explosion\yPos+i)
					spielfeld(explosion\xPos,explosion\yPos+i,3)=1
					Exit
				EndIf
			Next
			
			;unten
			For i=1 To explosion\groesse
				If spielfeld(explosion\xPos,explosion\yPos+i,3)=1 explosion\unten=i
				If spielfeld(explosion\xPos,explosion\yPos+i,1)=1 Then
					explosion\unten=i
					spielfeld(explosion\xPos,explosion\yPos+i,3)=1
				EndIf
				If i=explosion\unten Exit
				
				;explosion kann sich ausbreiten
				If spielfeld(explosion\xPos,explosion\yPos+i,0)<1 Then
					
					spielfeld(explosion\xPos,explosion\yPos+i,3)=1
					If i<explosion\groesse Then
						DrawImage imgFeuerSenkrecht,explosion\xPos*32,(explosion\yPos+i)*32,frame 
					Else
						DrawImage imgFeuerEndeUnten,explosion\xPos*32,(explosion\yPos+i)*32,frame
					EndIf  

				EndIf 
				
				;explosion gegen block
				If spielfeld(explosion\xPos,explosion\yPos+i,0)=1 Then
					explosion\unten=i
					Exit
				EndIf
				
				;explosion gegen zerstörbar
				If spielfeld(explosion\xPos,explosion\yPos+i,0)=2 Then
					explosion\unten=i
					steinZerstoert(explosion\xPos,explosion\yPos+i)
					spielfeld(explosion\xPos,explosion\yPos+i,3)=1
					Exit
				EndIf
			Next

		Else 
			Delete explosion
		EndIf
		
	Next

End Function