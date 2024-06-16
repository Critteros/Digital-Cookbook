<script lang="ts">
  import {
    DropdownMenu,
    DropdownMenuTrigger,
    DropdownMenuLabel,
    DropdownMenuContent,
    DropdownMenuSeparator,
    DropdownMenuItem,
  } from '@/components/ui/dropdown-menu';
  import { Avatar, AvatarFallback } from '@/components/ui/avatar';
  import { KeyOutline } from '@/components/icons';

  import { t } from '$lib/translations';

  import { getUserContext } from '$lib/context/userContext';

  const user = getUserContext();
</script>

<DropdownMenu>
  <DropdownMenuTrigger>
    <div class="flex items-center gap-2">
      <Avatar>
        <AvatarFallback>{$user?.username.slice(0, 2)}</AvatarFallback>
      </Avatar>
    </div>
  </DropdownMenuTrigger>
  <DropdownMenuContent>
    <DropdownMenuLabel>{$user?.username}</DropdownMenuLabel>
    <DropdownMenuSeparator />
    <DropdownMenuItem href="/logout" class="flex flex-row items-center justify-start gap-2">
      <KeyOutline width={18} height={18} />
      {$t('common.logout')}
    </DropdownMenuItem>
  </DropdownMenuContent>
</DropdownMenu>
