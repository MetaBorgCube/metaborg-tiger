# /bin/sh

for file in $1/*
do
  echo $file
  testName=`basename $file .tig`
  #testFileSyntax="appel/syntax/$testName.spt"
  testFileStatics="statics/statements/$testName.spt"
  echo $testName
  #echo $testFileSyntax
  echo $testFileStatics
    
  #echo "module $testName\n" >  $testFileSyntax
  #echo "language tiger\n"   >> $testFileSyntax
  #echo "test $testName [["  >> $testFileSyntax
  #cat $file                 >> $testFileSyntax
  #echo "]] parse succeeds"  >> $testFileSyntax
  
  echo "module $testName\n"   >  $testFileStatics
  echo "language tiger\n"     >> $testFileStatics
  echo "test $testName [["    >> $testFileStatics
  cat $file                   >> $testFileStatics
  echo "]] analysis succeeds" >> $testFileStatics
  
done