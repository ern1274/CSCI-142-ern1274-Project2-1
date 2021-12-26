package puzzles.solver;

import puzzles.Configuration;
import puzzles.clock.Clock;
import puzzles.clock.ClockConfig;
import puzzles.water.Water;
import puzzles.water.WaterConfig;

import java.util.*;

public class Solver {

    /**
     * The BFS method where it sorts through all the successors until it matches the goal
     * regardless of the type of config you have
     * @param problem: the original config
     * @return a list of configs that lead up to the goal config
     */
    public Optional<List<Configuration>> doBFS(Configuration problem) {
        int unique = 0;
        int total = 0;
        List<Configuration> queue = new LinkedList<>();
        queue.add(problem);
        Map<Configuration, Configuration> predecessors = new HashMap<>();
        predecessors.put(problem, null);
        while (!queue.isEmpty()) {
            Configuration current = queue.remove(0);
            if (current.isGoal()) {
                break;
            }

            for (Configuration ed : current.getSuccessors()) {
                total++;
                if (!predecessors.containsKey(ed)) {
                    unique++;
                    predecessors.put(ed, current);
                    queue.add(ed);
                }
            }
        }
        List<Configuration> path = new LinkedList<>();
            boolean test = false;
            Configuration curr = null;
            for (Configuration pre: predecessors.keySet()) {
                if(pre.isGoal()){
                    test = true;
                    curr = pre;
                }
            }
            if (test) {
                while (!curr.equals(problem)) {
                    path.add(0, curr);
                    curr = predecessors.get(curr);
                }
                path.add(0, curr);
            }
        System.out.println("Total Configs: "+total+"\nUnique Configs: "+unique);
        return Optional.of(path);
        }
}

