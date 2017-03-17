#!/bin/bash

if [ -f $HOME/app/BOOT-INF/classes/dump.sh ];
then
  export DUMP_SH_FILE=$HOME/app/BOOT-INF/classes/dump.sh
else
  export DUMP_SH_FILE=$HOME/dump.sh
fi

chmod 755 $DUMP_SH_FILE
$DUMP_SH_FILE &
