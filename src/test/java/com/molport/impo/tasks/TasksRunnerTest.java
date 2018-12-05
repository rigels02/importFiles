package com.molport.impo.tasks;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.molport.impo.out.OutputFormater;
import com.molport.impo.parsers.Parser_v2;

public class TasksRunnerTest {
	
	private  int taskNum=0;

	class Task implements Callable<String>{

		private int id;
		
		public Task(int id) {
			this.id = id;
		}

		@Override
		public String call() throws Exception {
			System.out.println("Started task NUM: "+id+", "+
					Thread.currentThread().getName()+
					"Time(ms):\t"+System.currentTimeMillis());
			Thread.sleep(1000);
			long time = System.currentTimeMillis();
			return "Task: "+id+" done! "+Thread.currentThread().getName()+" Time(ms):\t"+time;
		}
		
		
	}
	
	@Test
	public void test_ThreadsAndPool() {
		
		List<Callable<String>> callableTasks = new ArrayList<>();
		List<Future<String>> futures = new ArrayList<>();
		int remainingTaskNumber= 10;
		
		ExecutorService service = Executors.newFixedThreadPool(5 );
		
		
		while(remainingTaskNumber >0) {
			taskNum++;
			callableTasks.add(new Task(taskNum));
			remainingTaskNumber--;
			
		}
		try {
			futures = service.invokeAll(callableTasks);
			System.out.println(">>>>>>>>>All tasks invoked........");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Future<String> future : futures) {
			
			try {
				
				System.out.println("-> "+future.get());
				
			}catch (Exception  e) {
				System.err.println(e.getMessage());
			}
		}

		
	}
	
	@Test
	public void test_TaskRunner_with_ImpoTasks() {
		String[] files = new String[] {
			"src/test/data/input_02.sdf",
			"src/test/data/input_2.sdf",
			"src/test/data/input_3.sdf",
			"src/test/data/input_4.sdf",
			"src/test/data/input_5.sdf"
		}; 
		
		
		Parser_v2 parser = new Parser_v2();
		OutputFormater oFmt = new OutputFormater();
		PrintStream ps = null;
		try {
			ps = new PrintStream("src/test/data/output_1.txt");
			oFmt.printHEADER(ps);
			
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
			
		}
		TasksRunner tasksRunner = new TasksRunner();
		tasksRunner.runTasks(files, parser, oFmt, ps);
	}

	@Test
	public void test_TaskRunner_with_ImpoTasks_1() {
		String[] files = new String[] {
			"src/test/data1/file_01.sdf",
			"src/test/data1/file_02.sdf",
			"src/test/data1/file_03.sdf",
			"src/test/data1/file_04.sdf",
			"src/test/data1/file_05.sdf"
		}; 
		
		
		Parser_v2 parser = new Parser_v2();
		OutputFormater oFmt = new OutputFormater();
		PrintStream ps = null;
		try {
			ps = new PrintStream("src/test/data/output_1.txt");
			oFmt.printHEADER(ps);
			
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
			
		}
		TasksRunner tasksRunner = new TasksRunner();
		tasksRunner.runTasks(files, parser, oFmt, ps);
	}
}
