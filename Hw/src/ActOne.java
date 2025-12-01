import components.map.Map;
import components.program.Program;
import components.statement.Statement;

private static void renameInstruction(Program p, String oldName, String newName) {
    Map<String, Statement> context = p.newContext();
    Map<String, Statement> newCon = p.newContext();
    Statement body = p.newBody();
    p.swapContext(context);
    p.swapBody(body);

    while (context.size() > 0) {
        Map.Pair<String, Statement> pair = context.removeAny();
        renameInstruction(pair.value(), oldName, newName);
        if (pair.key().equals(oldName)) {
            newCon.add(newName, pair.value());
        } else {
            newCon.add(pair.key(), pair.value());
        }
    }
    renameInstruction(body, oldName, newName);

    p.swapBody(body);
    p.swapContext(newCon);
}
