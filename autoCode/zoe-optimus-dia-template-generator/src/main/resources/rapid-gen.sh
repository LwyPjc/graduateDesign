
#export CLASSPATH=.:./lib/*

java -classpath '.:./lib/*' -Xms128m -Xmx384m com.zoe.optimus.generator.ext.CommandLine -DtemplateRootDir=template
