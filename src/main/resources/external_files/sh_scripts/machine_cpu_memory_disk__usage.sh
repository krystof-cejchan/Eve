#!/bin/bash
# shellcheck disable=SC2046
# shellcheck disable=SC2006
# shellcheck disable=SC2005
echo `date`
#cpu use threshold
cpu_threshold='80'
 #mem idle threshold
mem_threshold='100'
 #disk use threshold
disk_threshold='90'
#---cpu
cpu_usage () {
# shellcheck disable=SC2006
cpu_idle=`top -b -n 1 | grep Cpu | awk '{print $8}'|cut -f 1 -d "."`
# shellcheck disable=SC2086
# shellcheck disable=SC2006
# shellcheck disable=SC2003
cpu_use=`expr 100 - $cpu_idle`
 echo "cpu utilization: $cpu_use"
if [ $cpu_use -gt $cpu_threshold ]
    then
        echo "cpu warning!!!"
    else
        echo "cpu ok!!!"
fi
}
#---mem
mem_usage () {
 #MB units
# shellcheck disable=SC2006
mem_free=`free -m | grep "Mem" | awk '{print $4+$6}'`
 echo "memory space remaining : $mem_free MB"
if [ "$mem_free" -lt $mem_threshold  ]
    then
        echo "mem warning!!!"
    else
        echo "mem ok!!!"
fi
}
#---disk
disk_usage () {
# shellcheck disable=SC2006
disk_use=`df -P | grep /dev | grep -v -E '(tmp|boot)' | awk '{print $5}' | cut -f 1 -d "%"`
 echo "disk usage : $disk_use"
if [ "$disk_use" -gt $disk_threshold ]
    then
        echo "disk warning!!!"
    else
        echo "disk ok!!!"
fi


}
cpu_usage
mem_usage
disk_usage


sleep 200000
exit 1