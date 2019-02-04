all:
	javac -sourcepath src -classpath lib/jfxrt.jar -d bin src/br/ufmg/dcc/pm/uno/UnoDesktop.java

clean:
	rm -rf bin/*

run:
	java -classpath src:bin:lib/jfxrt.jar br.ufmg.dcc.pm.uno.UnoDesktop
