package act.coaching.jigsawandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import act.coaching.jigsawandroid.R;
import act.coaching.jigsawandroid.model.Project;

/**
 * Created by actmember on 2018. 1. 24..
 */

public class ProjectListAdapter extends BaseAdapter{
    private ArrayList<Project> list;
    private Context mContext;
    public ProjectListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(ArrayList<Project> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        Holder holder;
        if(view == null) {
            view = View.inflate(mContext, R.layout.apdater_project, null);
            holder = new Holder();
            holder.setProjectName((TextView) view.findViewById(R.id.project_name));
            holder.setProjectType((TextView) view.findViewById(R.id.project_type));
            holder.setProjectRemove((TextView) view.findViewById(R.id.project_remove));
        } else {
            holder = (Holder) view.getTag();
        }

        holder.getProjectName().setText(list.get(position).getProjectName());
        holder.getProjectType().setText(list.get(position).getAssignType());
        holder.getProjectRemove().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        view.setTag(holder);
        return view;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }


    private class Holder {
        private TextView projectName;
        private TextView projectType;
        private TextView projectRemove;

        TextView getProjectName() {
            return projectName;
        }

        void setProjectName(TextView projectName) {
            this.projectName = projectName;
        }

        TextView getProjectType() {
            return projectType;
        }

        void setProjectType(TextView projectType) {
            this.projectType = projectType;
        }

        TextView getProjectRemove() {
            return projectRemove;
        }

        void setProjectRemove(TextView projectRemove) {
            this.projectRemove = projectRemove;
        }
    }
}
