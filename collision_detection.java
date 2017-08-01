

//You can for example use a 2D array with Boolean or integer values that represent presence of a square on a location: int[][] squares = new int[20][10];


//The following method finds the collision
public boolean isValidAndEmpty(TileType type, int x, int y, int rotation)
{

        //Ensure the piece is in a valid column.
        if(x < -type.getLeftInset(rotation) ||
            x + type.getDimension() - type.getRightInset(rotation) >= COL_COUNT)
        {
            return false;
        }

        //Ensure the piece is in a valid row.
        if(y < -type.getTopInset(rotation) ||y + type.getDimension() -type.getBottomInset(rotation) >= ROW_COUNT)
        {
            return false;
        }


         /*
         * Loop through every tile in the piece and see if it conflicts
         * with an existing tile.
         * Note: It's fine to do this even though it allows for wrapping * because we've already
         * checked to make sure the piece is in a valid location.*/
        for(int col = 0; col < type.getDimension(); col++)
{
            for(int row = 0; row < type.getDimension(); row++)
            {
            if(type.isTile(col, row, rotation) &&
                    isOccupied(x + col, y + row))
            {
                return false;

        }
}

}
                return true;

}
