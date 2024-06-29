class Solution {

    List<String> result = new ArrayList();

    public String[] expand(String s) {

        if(s == null || s.length() == 0) return new String[]{};

        int i = 0;
        List<List<Character>> blocks = new ArrayList();

        while(i < s.length())
        {
            List<Character> block = new ArrayList();

            char c = s.charAt(i);

            if(c == '{')
            {
                // All the characters in {} must reside as a single block

                // This is bcoz c right now is {
                i++;

                while(s.charAt(i) != '}')
                {
                    if(s.charAt(i) != ',') block.add(s.charAt(i));

                    i++;
                }
            }
            else
            {
                // This is a normal character
                block.add(c);
            }

            // To maintain lexicographic order
            Collections.sort(block);
            blocks.add(block);
            i++;
        }

        // DFS on result

        helper(blocks, 0, new StringBuilder());

        String[] answer = new String[result.size()];

        for(int j = 0; j < result.size(); j++) answer[j] = result.get(j);

        return answer;
    }

    private void helper(List<List<Character>> blocks, int index, StringBuilder sb)
    {
        if(index == blocks.size())
        {
            result.add(sb.toString());
            return;
        }

        List<Character> block = blocks.get(index);

        // In each and every block we need to start from the first character so i starts from 0
        for(int i = 0; i < block.size(); i++)
        {
            char c = block.get(i);

            // action
            sb.append(c);

            // recurse
            helper(blocks, index + 1, sb);

            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
