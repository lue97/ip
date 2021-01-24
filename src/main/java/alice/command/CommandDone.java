package alice.command;

import alice.Alice;
import alice.AliceException;
import alice.task.Task;
import alice.task.TaskList;

import java.util.List;
import java.util.stream.Collectors;

public class CommandDone extends Command {

	/** Usage details. **/
	private static final String usage = "done Usage: done [index]";

	/**
	 * {@inheritDoc}
	 */
	public CommandDone(String[] tokens) {
		super(tokens);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Alice execute(Alice agent) {
		Alice newAgent;
		try {
			int index = Integer.parseInt(tokens[1]);
			if (index < 0 || index > agent.getData().count()) {
				throw new AliceException(String.format("Index %d out of bounds for schedule of size %d.",
						index, agent.getData().count()));
			}
			List<Task> dataList = agent.getData().getTasks().stream().map(Task::clone).collect(Collectors.toList());
			dataList.set(index - 1, dataList.get(index - 1).setDone(true));
			String response = String.format(Alice.TASK_DONE, dataList.get(index - 1));
			newAgent = new Alice(response, new TaskList(dataList), agent.getDone(), true);
		} catch (NumberFormatException numberFormatException) {
			newAgent = new Alice("Invalid number", agent.getData(), agent.getDone(), false);
		} catch (AliceException aliceException) {
			newAgent = new Alice(aliceException.getMessage(), agent.getData(), agent.getDone(), false);
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exception) {
			newAgent = new Alice(usage, agent.getData(), agent.getDone(), false);
		}
		return newAgent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}
}
