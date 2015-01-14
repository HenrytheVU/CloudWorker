package com.henry.websocket;

import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {

	// public static void main(String[] args) {
	// List<int[]> jobs = calculateJobs(13);
	//
	// List<List<int[]>> parts = chopListIntoParts(jobs, 4);
	// System.out.println("Number of Parts : " + parts.size());
	//
	// for (List<int[]> part : parts) {
	// System.out.println("");
	// for (int[] job : part) {
	// System.out.println(job[0] + "-" + job[1]);
	// }
	// }
	// }

	public static List<String> chopJobsIntoPartsAsCSV(String jobs,
			final int parts) {
		System.out.println("Chopping Jobs...");

		List<String> result = new ArrayList<String>();

		String[] jobsSplit = jobs.split(",");
		int numberOfJobs = jobsSplit.length;
		System.out.println("Number Of Jobs : " + numberOfJobs);
		int numberOfJobsLeft = numberOfJobs;
		int denominatorRemainder = parts;
		int lastIndex = 0;
		int partCounter = 0;
		if (numberOfJobs % parts != 0) {
			System.out.println("Cannot be devided into Int...");
			int moduloRest = numberOfJobs % parts;
			int partLength = (numberOfJobs - moduloRest) / parts;
			for (int i = 0; i < numberOfJobs; i += (partLength + 1)) {
				lastIndex = i;
				if (numberOfJobsLeft % denominatorRemainder != 0) {
					numberOfJobsLeft -= (partLength + 1);
					denominatorRemainder--;
					System.out.println("numberOfJobsLeft : " + numberOfJobsLeft
							+ " | denominatorRemainder : "
							+ denominatorRemainder);
					if (i + partLength < numberOfJobs) {
						System.out.println("currentIndex : " + i);
						String part = "";
						for (int currentIndex = i; currentIndex <= (i + partLength); currentIndex++) {
							part += jobsSplit[currentIndex] + ",";
						}
						System.out.println("part "+ partCounter++ + " : " + part);
						result.add(part);
					} else {
						String part = "";
						System.out.println("last if");
						for (int currentIndex = i; currentIndex <= numberOfJobs; currentIndex++) {
							part += jobsSplit[currentIndex] + ",";
						}
						System.out.println("part "+ partCounter++ + " : " + part);
						result.add(part);
					}
				} else {
					int regularLength = numberOfJobsLeft / denominatorRemainder;
					for (int a = lastIndex; a < numberOfJobs - 1; a += regularLength) {
						String part = "";
						for (int currentIndex = a; currentIndex < (a + regularLength); currentIndex++) {
							part += jobsSplit[currentIndex] + ",";
						}
						System.out.println("part " + partCounter++ + " : " + part);
						result.add(part);
					}
					break;
				}
			}
		} else {
			int regularLength = numberOfJobsLeft / parts;
			System.out.println("Regular Length : " + regularLength);
			for (int i = 0; i < numberOfJobs; i += regularLength) {
				String part = "";
				for (int currentIndex = i; currentIndex < (i + regularLength); currentIndex++) {
					part += jobsSplit[currentIndex] + ",";
				}
				System.out.println("part " + partCounter++ + " : " + part);
				result.add(part);
			}
		}
		return result;
	}

	public static <T> List<List<T>> chopListIntoParts(List<T> list,
			final int numberOfParts) {
		List<List<T>> parts = new ArrayList<List<T>>();

		int listSize = list.size();
		int listRemainder = listSize;
		int denominatorRemainder = numberOfParts;
		int lastIndex = 0;
		System.out.println("Listsize : " + listSize);
		if (listSize % numberOfParts != 0) {

			System.out.println("Cannot be devided into Int...");
			int moduloRest = listSize % numberOfParts;
			int length = (listSize - moduloRest) / numberOfParts;
			System.out.println("Length : " + (length + 1));
			for (int i = 0; i < listSize; i += (length + 1)) {
				lastIndex = i;
				if (listRemainder % denominatorRemainder != 0) {
					listRemainder -= (length + 1);
					denominatorRemainder--;
					System.out.println("listRemainder : " + listRemainder
							+ " | denominatorRemainder : "
							+ denominatorRemainder);
					if (i + length < listSize) {
						parts.add(new ArrayList<T>(list.subList(i,
								(i + length + 1))));
						System.out.println("From " + i + " to "
								+ (i + length + 1));
					} else {
						parts.add(new ArrayList<T>(list.subList(i, (listSize))));
						System.out.println("From " + i + " to end "
								+ (listSize));
					}
				} else {
					int regularLength = listRemainder / denominatorRemainder;
					System.out.println("regular length : " + regularLength);
					for (int a = lastIndex; a < listSize - 1; a += regularLength) {
						System.out.println("From " + a + " to "
								+ (a + regularLength));
						parts.add(new ArrayList<T>(list.subList(a, a
								+ regularLength)));
					}
					break;
				}
			}
		} else {
			int regularLength = listSize / numberOfParts;
			for (int i = 0; i < listSize; i += regularLength) {
				System.out.println("From " + i + " to " + (i + regularLength));
				parts.add(new ArrayList<T>(list.subList(i, i + regularLength)));
			}
		}
		return parts;
	}

	public static List<int[]> calculateJobs(int numberOfFiles) {
		List<int[]> result = new ArrayList<int[]>();
		int[] job;
		for (int i = 0; i < numberOfFiles - 1; i++) {
			for (int j = i + 1; j <= numberOfFiles - 1; j++) {
				job = new int[2];
				job[0] = i;
				job[1] = j;
				result.add(job);
			}
		}
		System.out.println("Number of Jobs : " + result.size());
		return result;
	}

	public static String calculateJobsAsCSV(int numberOfFiles) {
		String result = "";
		for (int i = 0; i < numberOfFiles - 1; i++) {
			for (int j = i + 1; j <= numberOfFiles - 1; j++) {
				String file1 = String.valueOf(i);
				String file2 = String.valueOf(j);
				result += file1 + "-" + file2 + ",";
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String jobs = LoadBalancer.calculateJobsAsCSV(100);
		System.out.println(jobs);
		List<String> parts = LoadBalancer.chopJobsIntoPartsAsCSV(jobs, 7);

		for(String part : parts) {
			System.out.println("part : " + part.split(",").length);
		}
//		LoadBalancer.chopListIntoParts(LoadBalancer.calculateJobs(1000), 8);
	}
}
