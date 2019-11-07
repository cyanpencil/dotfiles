


# ASL-Fall18


This is my project for the Advanced Systems Lab class of 2018. 
I got the maximum grade available. You can see the the grade report in file `grade_report.pdf`.


Brief explanation of the directory structure:


- `src`: java middleware sources
- `report`: tex files and images for generating the final pdf
- `util`: some utilities scripts, not vital for the project
- `resp_time_distributions`: contains logs of percentiles of response times
	measured on the middleware, for both the *sharded* and *non sharded* sections
	of Section 5
- `archivio`: where most of the logs are kept, in the following directory tree:
	- `baseline_1_real`: logs for Section 2.1
	- `baseline_2_real`: logs for Section 2.2
	- `middleware_1_real`: logs for Section 3.1
	- `middleware_2_get_real` and `middleware_2_set_real`: logs for Section 3.2
	- `writes_1_real`: logs for Section 4
	- `gets_1_real`: logs for Section 5, non sharded
	- `gets_2_real`: logs for Section 5, sharded
	- `twok`: logs for Section 6
- `scp_logs`: where temporary logs where kept. These were not uploaded to gitlab for
	space reasons, but are still available locally on my machine.
- `ssh_logs`: where output of ssh was piped, to check correct execution of experiments.

The `*.sh` files in the home directory where used to start and manage experiments.
The `*.py` files in the home directory where used to parse logs and draw plots.

## Log names

In each folder, logs are grouped by the hostname of the machine they were gathered from.

So for example, inside `archivio/scp_logs/writes_1_real/scp_logs/writes_1/` we can see the directories:

- `storewz2nt2k6rryc2sshpublicip1.westeurope.cloudapp.azure.com_0`
- `storewz2nt2k6rryc2sshpublicip1.westeurope.cloudapp.azure.com_1`
- `storewz2nt2k6rryc2sshpublicip2.westeurope.cloudapp.azure.com_0`
- `storewz2nt2k6rryc2sshpublicip2.westeurope.cloudapp.azure.com_1`
- `storewz2nt2k6rryc2sshpublicip3.westeurope.cloudapp.azure.com_0`
- `storewz2nt2k6rryc2sshpublicip3.westeurope.cloudapp.azure.com_1`
- `storewz2nt2k6rryc2sshpublicip4.westeurope.cloudapp.azure.com`
- `storewz2nt2k6rryc2sshpublicip5.westeurope.cloudapp.azure.com`

The first six are the `memtier` logs, and the `_0` or `_1` suffix at the end represent
if it was the `memtier` instance connected to the first or to the second middleware.

Inside one of the folders relative to memtier, we can find many logs with filenames like:

`sets_1_clients_rep_1_workers_8.log` - that means it's the log relative to the first repetition
of the experiment with 8 worker threads and only one client. The log contains the normal
console output of memtier.

The final two folders, the one which contain `publicip4` and `publicip5` contain the 
logs of the middlewares. They follow the same naming convention as before, but the content
is different: they are comma separated values files containing the following information:

- `time`: Timestamp of when the sampler gathered this particular line of data
- `thread`: Which worker thread this line of data is relative to
- `resp_time`: Average response time of requests handled by this thread
- `throughput`: Average throughput of this thread
- `queue_time`: Average queue time of requests handled by this thrad
- `queue_size`: Average queue size
- `calculating`: Average time spent by this thread parsing requests
- `waiting`: Average time spent by this thread waiting for an answer from `memcached`
- `mainthread`: Average time spent by the network thread parsing requests
- `gets`: Number of gets processed by this thread
- `sets`: Number of sets processed by this thread


